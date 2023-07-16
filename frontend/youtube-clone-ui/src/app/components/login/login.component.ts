import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  sessionId: any = "";
  userName! : string;
  isAuthenticated : boolean = false;

  constructor(private router: Router,
    private http: HttpClient,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.model.username, this.model.password).subscribe(data => {
      if (data) {
        this.sessionId = data.sessionId;
        this.userName = data.username;

        sessionStorage.setItem(
          'token',
          this.sessionId
        )

        sessionStorage.setItem(
          'username',
          this.userName
        )
        this.authService.setAuthenticationStatus(true);
        this.authService.setUsername(this.userName);
        this.router.navigate(['']);
      } else {
        alert("Authentication failed.")
      }
    })
  }

}
