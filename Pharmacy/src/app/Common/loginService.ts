import { Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Service()
export class LoginService {


  constructor() {
    this.isLogin = localStorage.getItem('isLogin') =='askldhjkjashdhjkdsagfjhds';
  }

  private isLogin: boolean = false;


  getIsLogin(): boolean {
    return this.isLogin;
  }

  setIsLogin(value: boolean) {
    this.isLogin = value;
    localStorage.setItem('isLogin',value?'askldhjkjashdhjkdsagfjhds':'iueyrjds,n.masdslafm');
  }


}
