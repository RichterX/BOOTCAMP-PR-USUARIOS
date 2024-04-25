import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { UserListComponent } from './entities/user/user-list/user-list.component';
import { UserFormComponent } from './entities/user/user-form/user-form.component';
import { HttpRequestIntercept } from './config/interceptors/http-request-interceptor.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    UserListComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ButtonModule,
    AutoCompleteModule
  ],
  providers: [
    { //Especificamos el interceptor para todas las peticiones aquí, en la clase creada.
      provide: HTTP_INTERCEPTORS, //HTTP_INTERCEPTORS es una constante de angular
      useClass: HttpRequestIntercept, //Clase creada para interceptar todas las peticiones. Está en app.config
      multi: true //Permite que se puedan interceptar varias peticiones
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
