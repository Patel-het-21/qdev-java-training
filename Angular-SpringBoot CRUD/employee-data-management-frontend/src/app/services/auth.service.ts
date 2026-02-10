import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { RegisterRequest, LoginRequest, UserResponse } from '../models/user.model';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:9090/api/v1/auth';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService
  ) { }

  // Register new company/user
  register(data: RegisterRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/register`, data).pipe(
      tap(user => this.sessionService.setUser(user))
    );
  }

  // Login
  login(data: LoginRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/login`, data).pipe(
      tap(user => this.sessionService.setUser(user))
    );
  }

  // Logout
  logout(): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/logout`, {}).pipe(
      tap(() => this.sessionService.clearUser())
    );
  }

  checkEmail(email: string) {
  return this.http.get<boolean>(
    `${this.baseUrl}/check-email`,
    { params: { email } }
  );
}

checkCompany(companyName: string) {
  return this.http.get<boolean>(
    `${this.baseUrl}/check-company`,
    { params: { companyName } }
  );
}

}