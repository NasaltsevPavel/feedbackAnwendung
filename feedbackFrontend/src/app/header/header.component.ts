import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {MatIcon} from "@angular/material/icon";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    MatIcon,
    NgIf
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(private router: Router) {}

  isMainPage(): boolean {
    return this.router.url === '/main-page';
  }
}
