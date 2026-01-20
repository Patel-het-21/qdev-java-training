import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'https://jsonplaceholder.typicode.com/users';
  //private apiUrl = 'https://jsonplaceholder.typicode.com/posts';

  constructor(private http: HttpClient) {}

  // GET
  getUsers(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  // POST
  addUser(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
}
