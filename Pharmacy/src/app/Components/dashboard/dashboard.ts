import { Component } from '@angular/core';
import { ButtonDirective, ButtonLabel } from 'primeng/button';

@Component({
  imports: [ButtonDirective, ButtonLabel],
  selector: 'NTG-dashboard',
  styleUrl: './dashboard.css',
  templateUrl: './dashboard.html',
})
export class Dashboard {}
