import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getAllUsers(): Observable<User[]> {
    const urlEndpoint = 'http://localhost:8080/users';
    return this.http.get<User[]>(urlEndpoint);
  }
}
