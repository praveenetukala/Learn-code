import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  @ViewChild('loginModal') loginModal!: ElementRef;

  openLoginModal() {
    console.log('openLoginModal()');
    this.loginModal.nativeElement.classList.add('show');
    this.loginModal.nativeElement.style.display = 'block';
    
  }

  login() {
    alert('Login success');
  }
}
