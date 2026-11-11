import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { LoginService } from '../../Common/loginService';
import { Router } from '@angular/router';

@Component({
  imports: [CommonModule, FormsModule, ButtonModule, CheckboxModule, InputTextModule],
  selector: 'NTG-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  constructor(private _login:LoginService,private _router : Router) {
  }

  protected HandleLogin() {
    this._login.setIsLogin(true);
    this._router.navigate(['home']);

  }
}
