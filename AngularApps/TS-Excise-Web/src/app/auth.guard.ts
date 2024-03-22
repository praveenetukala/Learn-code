import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from './services/login.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(): boolean {
    if (this.loginService.isLoggedIn()) {
      console.log("True is logged in ")
      return true;
    } else {
      console.log("True is not logged in ")
      this.router.navigate(['login']);
      return false;
    }
  }
}
