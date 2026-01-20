import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

interface Student {
  name: string;
  age: number;
}

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent {
  studentName: string = "Manan";
  studentAge: number = 22;

  students: Student[] = [
    { name: 'Manan', age: 22 },
    { name: 'Sumit', age: 23 },
    { name: 'Chandan', age: 45}
  ]

  currentIndex: number = 0;

  get student(): Student{
    return this.students[this.currentIndex];
  }

  changeStudent(){
    this.currentIndex = (this.currentIndex + 1) % this.students.length;
  }
  
    onSubmit(from: NgForm) {
      console.log(from.value);
    }

  /* isFirst: boolean = true;

  changeStudent() {
    if (this.isFirst) {
      this.studentName = "Rahul";
      this.studentAge = 28;
    } else {
      this.studentName = "Manan";
      this.studentAge = 22;
    }
    this.isFirst = !this.isFirst;
  } */
}