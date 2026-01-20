import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-teacher',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './teacher.component.html',
  styleUrl: './teacher.component.css'
})
export class TeacherComponent implements OnInit{

  teacherForm!: FormGroup;

  constructor(private fb: FormBuilder){}

  ngOnInit(): void {
    this.teacherForm = this.fb.group({
      name : ['', Validators.required],
      subject: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(1)]]
    });
  }

  submitForm(){
    if(this.teacherForm.valid){
      console.log("Teacher Form Date : ",this.teacherForm.value);
    }
  }
}