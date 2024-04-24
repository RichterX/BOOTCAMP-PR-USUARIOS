import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  //Obtenemos todos los usuarios
  public getAllUsers(): Observable<User[]> {
    const urlEndpoint = 'http://localhost:8080/users/';
    return this.http.get<User[]>(urlEndpoint);
  }

  //Obtenemos todos los usuarios paginados y ordenados según criterio
  public getAllUsersPaginated(page: number, size: number, sort: string, filters?: string): Observable<User[]> {
    let urlEndpoint = 'http://localhost:8080/users/search?page=' + page + '&size=' + size + '&sort=' + sort;
    if (filters)
      {
        urlEndpoint = urlEndpoint + "&" + filters;
      }
    return this.http.get<User[]>(urlEndpoint);
  }

  //Creación de usuarios
  public createUser(user: User): Observable<User> {
    const urlEndpoint = 'http://localhost:8080/users/users';
    return this.http.post<User>(urlEndpoint, user);
  }

  //Actualización de usuarios
  public updateUser(user: User): Observable<User> {
    const urlEndpoint = 'http://localhost:8080/users/users';
    return this.http.patch<User>(urlEndpoint, user);
  }

  //Eliminación de usuarios
  public deleteUser(id: number): Observable<any> {
    const urlEndpoint = 'http://localhost:8080/users/' + id;
    return this.http.delete<any>(urlEndpoint);
  }

  //Obtención de un usuario por su id
  public getUserById(id: number): Observable<User> {
    const urlEndpoint = 'http://localhost:8080/users/' + id;
    return this.http.get<User>(urlEndpoint);
  }
}
