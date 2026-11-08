

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';

@Component({
  imports: [CommonModule, ButtonModule],
  selector: 'NTG-main',
  styleUrl: './main.css',
  templateUrl: './main.html',
})
export class Main {}
