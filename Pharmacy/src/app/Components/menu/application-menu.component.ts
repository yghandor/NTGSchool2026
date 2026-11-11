import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Menubar } from 'primeng/menubar';
import { Badge } from 'primeng/badge';
import { Ripple } from 'primeng/ripple';
import { NgClass, NgIf } from '@angular/common';
import { Avatar } from 'primeng/avatar';
import { Menu, MenuModule } from 'primeng/menu';
import { LoginService } from '../../Common/loginService';
 import { InputText } from 'primeng/inputtext';

@Component({
  imports: [Menubar, Badge, Ripple, NgClass, Avatar, Menu, InputText, NgIf, MenuModule],
  selector: 'NTG-menu',
  styleUrl: './application-menu.component.css',
  templateUrl: './application-menu.component.html',
})
export class ApplicationMenu {
  constructor(private _login: LoginService) {}

  items: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: 'dashboard',
    },
    {
      label: 'UI Kit',
      icon: 'pi pi-pencil',
      routerLink: 'process',
    },
  ];

  avatarMenuItem: MenuItem[] = [
    {
      label: 'log out',
      command: (event) => {
        this.logMeOut();
      },
    },
  ];

  logMeOut() {
    this._login.setIsLogin(false);
    window.location.reload();
  }
}
