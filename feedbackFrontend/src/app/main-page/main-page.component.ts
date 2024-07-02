import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatCard} from "@angular/material/card";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    RouterLink,
    MatCard
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {

}
