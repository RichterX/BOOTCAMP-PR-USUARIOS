import{
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpErrorResponse
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

export class HttpRequestIntercept implements HttpInterceptor {
    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        const retryNumber = 3;
        return next
            .handle(request)
            .pipe(
                retry(retryNumber),
                catchError((error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (error.status) {
                        // error en el lado del cliente
                        errorMessage = `Error Status: ${error.status}\nMessage: ${error.message}`;
                    } else {
                        // error en el lado del servidor
                        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
                    }
                    console.log(errorMessage);
                    return throwError(() => errorMessage);
                })
            );
    }
}