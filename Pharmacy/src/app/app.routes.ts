import { Routes } from '@angular/router';
import {authGuard} from './Common/auth-guard';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./Pages/login/login').then((m) => m.Login) },
  { path: 'home', loadComponent: () => import('./Pages/main/main').then((m) => m.Main),
  canActivate: [authGuard],},
  {path:'**',redirectTo:'/home'},
];
