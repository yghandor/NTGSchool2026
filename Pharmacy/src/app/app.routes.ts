import { Routes } from '@angular/router';
import { authGuard } from './Common/auth-guard';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./Pages/login/login').then((m) => m.Login) },
  {
    path: 'home',
    loadComponent: () => import('./Pages/main/main').then((m) => m.Main),
    canActivate: [authGuard],
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./Components/dashboard/dashboard').then((m) => m.Dashboard),
      },
      {
        path: 'process',
        loadComponent: () => import('./Pages/main/process/process').then((m) => m.Process),
      },
      { path: '**', redirectTo: 'dashboard' },
    ],
  },
  { path: '**', redirectTo: '/home' },
];
