import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAuthenticated : boolean = false
  username: string = '';

  constructor(private authService : AuthService,
              private route: Router) { }

  ngOnInit(): void {
    this.route.events.subscribe((val:any) => {
      this.checkLoginStatus();
    })
    
  }

  checkLoginStatus() {
    this.username = this.authService.getUsername();
    if(this.username != '') {
      this.isAuthenticated = true;
    }
  }

  logout() {
    this.route.events.subscribe((val:any) => {
    this.authService.logout();
    this.isAuthenticated = false;
    })
  }

  isNotLoginPage(): boolean {
    return this.route.url !== '/login';
  }

}
