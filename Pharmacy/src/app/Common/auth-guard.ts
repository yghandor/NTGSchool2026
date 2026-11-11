import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { LoginService } from './loginService';

export const authGuard: CanActivateFn = (route, state) => {
  const _login: LoginService = inject(LoginService);
  if(!_login.getIsLogin()){

      const _router = inject(Router);
      _router.navigate(['/login']);

  }
  return _login.getIsLogin();
};
