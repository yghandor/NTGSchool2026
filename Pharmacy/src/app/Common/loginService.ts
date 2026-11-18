import { Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Service()
export class LoginService {
  constructor() {
    this.isLogin = localStorage.getItem('isLogin') == 'askldhjkjashdhjkdsagfjhds';
    this.fullName = localStorage.getItem('fullName');
  }

  private isLogin: boolean = false;
  fullName: any = '';

  getIsLogin(): boolean {
    return this.isLogin;
  }

  setIsLogin(value: boolean, fullName: string) {
    this.isLogin = value;
    this.fullName = fullName;
    localStorage.setItem('isLogin', value ? 'askldhjkjashdhjkdsagfjhds' : 'iueyrjds,n.masdslafm');
    localStorage.setItem('fullName', fullName);
  }
}
