import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { LoginService } from '../../Common/loginService';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Toast } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  imports: [CommonModule, FormsModule, ButtonModule, CheckboxModule, InputTextModule, Toast],
  selector: 'NTG-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
  providers: [MessageService],
})
export class Login {
  userName: string = '';
  password: string = '';

  constructor(
    private _login: LoginService,
    private _router: Router,
    private _http: HttpClient,
    private messageService: MessageService,
  ) {}

  IsLoginRequestISRunning = signal<boolean>(false);

  protected HandleLogin() {
    this.IsLoginRequestISRunning.set(true);
    let url = '/rest/login';
    let body = {
      username: this.userName,
      password: this.password,
    };

    this._http.post(url, body).subscribe(
      (response: any) => {
        if (response.error) {
          this.messageService.add({
            severity: 'error',
            summary: 'Login Fail',
            detail: response.error.message,
          });
        } else {
          this._login.setIsLogin(true, response.fullName);
          this._router.navigate(['home']);
        }
        this.IsLoginRequestISRunning.set(false);
      },
      (error) => {
        console.log(error);
        this.messageService.add({
          severity: 'error',
          summary: 'Server Error',
          detail: 'Ops! Something went wrong! Please Check Your Admin',
        });

        this.IsLoginRequestISRunning.set(false);
      },
    );
  }
}
