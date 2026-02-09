import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserResponse } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private currentUserSubject = new BehaviorSubject<UserResponse | null>(this.getUserFromStorage());
  public currentUser$: Observable<UserResponse | null> = this.currentUserSubject.asObservable();

  constructor() { }

  // Get current user value
  get currentUserValue(): UserResponse | null {
    return this.currentUserSubject.value;
  }

  // Check if user is logged in
  isLoggedIn(): boolean {
    return this.currentUserValue !== null;
  }

  // Get tenant ID
  getTenantId(): string | null {
    return this.currentUserValue?.tenantSchema || null;
  }

  // Save user session
  setUser(user: UserResponse): void {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  // Clear user session
  clearUser(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
  // Get user from localStorage
  private getUserFromStorage(): UserResponse | null {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
  }
}