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
  userId : string ='';

  constructor(private authService : AuthService,
              private route: Router) { }

  ngOnInit(): void {
    this.route.events.subscribe((val:any) => {
    })
    
  }

  onLogout() {
    this.route.events.subscribe((val:any) => {
    this.authService.logout();
    })
  }

  loggedIn(): boolean {
    if (sessionStorage.getItem("token")) {
      this.username = this.authService.getUsername();
      return true;
    } else {
      return false;
    }
  }

 

}
