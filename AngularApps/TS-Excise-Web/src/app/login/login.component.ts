import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { LoginService } from '../services/login.service';
import { Login } from '../models/Login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  loginModel: Login = new Login();

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  login() {
    console.log('Username:', this.username);
    console.log('Password:', this.password);

    this.loginModel.userName = this.username;
    this.loginModel.password = this.password;

    let response = this.loginService.login(this.loginModel);

    response.subscribe(
      (success) => {
        console.log(success);
        console.log(success.token);

        sessionStorage.setItem('authToken', success.token);
        this.router.navigate(['dashboard']);
      },
      (error) => {
        alert('Login Failed something went wrong');
        console.log(error);
      }
    );
  }
}
