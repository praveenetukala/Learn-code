import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  API_BASE_URL = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  public login(login: any): Observable<any> {
    return this.http.post<any>(this.API_BASE_URL + 'user/login', login);
  }

  isLoggedIn(): boolean {
    const token = sessionStorage.getItem('authToken');
    return !!token;
  }
}
