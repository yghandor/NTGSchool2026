
 import { Component } from '@angular/core';
 import {Footer} from './Components/footer/footer';
 import {RouterOutlet} from '@angular/router';


@Component({
  selector: 'app-root',
  imports: [
    Footer,
    RouterOutlet
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

}
