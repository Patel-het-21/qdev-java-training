import { NgFor, NgIf, TitleCasePipe, UpperCasePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { CapitalizeNamePipe } from '../pipes/capitalize-name.pipe';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'; // <- add this


@Component({
  selector: 'app-api-demo',
  standalone: true,
  imports: [NgFor, NgIf, CapitalizeNamePipe, UpperCasePipe, MatProgressSpinnerModule],
  templateUrl: './api-demo.component.html',
  styleUrl: './api-demo.component.css'
})
export class ApiDemoComponent {
  users: any[] = [];
  loading = false;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers() {
    this.loading = true;
    this.apiService.getUsers().subscribe({
      next: (data) => { 
        this.users = data; 
        this.loading = false 
      },
      error: (err) => { 
        console.error(err); 
        this.loading = false 
      }
    });
  }
}