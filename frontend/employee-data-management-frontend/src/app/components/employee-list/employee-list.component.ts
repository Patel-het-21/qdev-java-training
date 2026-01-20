import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { ToastService } from '../../shared/toast.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: any[] = [];
  filteredEmployees: any[] = [];
  loading = true;

  searchTerm = '';

  // Pagination
  currentPage = 1;
  rowsPerPage = 10;
  totalPages = 0;
  rowsPerPageOptions = [10, 25, 50, 100];

  // Sorting
  currentSortField = 'id';
  currentSortOrder: 'asc' | 'desc' = 'asc';

  // Delete Modal
  showDeleteModal = false;
  employeeToDelete: number | null = null;

  constructor(
    private service: EmployeeService,
    private router: Router,
    private toast: ToastService
  ) { }

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees() {
    this.loading = true;
    this.service.getAllEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.filteredEmployees = [...this.employees];
        this.sortData(this.currentSortField, true);

        this.currentPage = 1;
        this.calculateTotalPages();
        this.loading = false;
      },
      error: () => {
        this.toast.show('Failed to load employees.', 'error');
        this.loading = false;
      }
    });
  }

  addEmployee() {
    this.router.navigate(['/add-employee']);
  }

  editEmployee(id: number) {
    this.router.navigate(['/add-employee'], { queryParams: { id } });
  }

  openDeleteModal(id: number) {
    this.employeeToDelete = id;
    this.showDeleteModal = true;
  }

  closeDeleteModal() {
    this.showDeleteModal = false;
    this.employeeToDelete = null;
  }

  confirmDelete() {
    if (!this.employeeToDelete) return;

    this.service.deleteEmployee(this.employeeToDelete).subscribe({
      next: () => {
        this.toast.show('Employee deleted successfully', 'success');
        this.closeDeleteModal();
        this.loadEmployees();
      },
      error: () => {
        this.toast.show('Failed to delete employee', 'error');
        this.closeDeleteModal();
      }
    });
  }

  downloadPdf(){
    this.service.downloadEmployeePdf().subscribe({
      next: (blob) =>{
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'employee.pdf';
        a.click();

        window.URL.revokeObjectURL(url);
      },
      error: () =>{
        this.toast.show('Failed to download PDF','error');
      }
    });
  }

  search() {
    const term = this.searchTerm.trim().toLowerCase();
    this.filteredEmployees = this.employees.filter(emp =>
      (emp.firstName + ' ' + emp.lastName).toLowerCase().includes(term)
    );
    this.currentPage = 1;
    this.calculateTotalPages();
  }

  sortData(field: string, initialLoad = false) {
    if (!initialLoad) {
      this.currentSortOrder = this.currentSortField === field
        ? (this.currentSortOrder === 'asc' ? 'desc' : 'asc')
        : 'asc';
      this.currentSortField = field;
    }

    this.filteredEmployees.sort((a, b) => {
      const multiplier = this.currentSortOrder === 'asc' ? 1 : -1;
      let valA: any;
      let valB: any;

      switch (field) {
        case 'id': valA = a.id; valB = b.id; break;
        case 'name': valA = (a.firstName + ' ' + a.lastName).toLowerCase(); valB = (b.firstName + ' ' + b.lastName).toLowerCase(); break;
        case 'dob': valA = new Date(a.dateOfBirth); valB = new Date(b.dateOfBirth); break;
        case 'age': valA = a.age; valB = b.age; break;
        default: valA = (a[field] || '').toString().toLowerCase(); valB = (b[field] || '').toString().toLowerCase();
      }

      if (valA < valB) return -1 * multiplier;
      if (valA > valB) return 1 * multiplier;
      return 0;
    });

    this.currentPage = 1;
    this.calculateTotalPages();
  }

  calculateTotalPages() {
    this.totalPages = Math.ceil(this.filteredEmployees.length / this.rowsPerPage) || 1;
  }

  changePage(page: number) {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
  }

  onRowsPerPageChange() {
    this.currentPage = 1;
    this.calculateTotalPages();
  }

  get startItem() {
    return this.filteredEmployees.length === 0
      ? 0
      : (this.currentPage - 1) * this.rowsPerPage + 1;
  }

  get endItem() {
    return Math.min(
      this.currentPage * this.rowsPerPage,
      this.filteredEmployees.length
    );
  }

  get paginatedEmployees() {
    const startIndex = (this.currentPage - 1) * this.rowsPerPage;
    const endIndex = startIndex + this.rowsPerPage;
    return this.filteredEmployees.slice(startIndex, endIndex);
  }

  trackByEmpId(index: number, emp: any) {
    return emp.id;
  }

}