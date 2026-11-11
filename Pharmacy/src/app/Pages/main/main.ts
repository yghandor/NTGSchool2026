

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ApplicationMenu } from '../../Components/menu/application-menu.component';
import { RouterOutlet } from '@angular/router';

@Component({
  imports: [CommonModule, ButtonModule, ApplicationMenu, RouterOutlet],
  selector: 'NTG-main',
  styleUrl: './main.css',
  templateUrl: './main.html',
})
export class Main {}
