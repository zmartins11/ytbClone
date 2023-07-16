import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseStatus } from '../responseStatus';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  isAuthenticated: boolean = false;
    username: string = '';
    authenticationStatusChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  setAuthenticationStatus(status: boolean): void {
    this.isAuthenticated = status;
    this.authenticationStatusChanged.emit(status);
  }

    constructor(private httpClient : HttpClient) { }

    login(username : string, password :string): Observable<ResponseStatus> {
        const body = {
            username: username,
            password: password
          };
        return this.httpClient.post<ResponseStatus>("http://localhost:9090/api/login", body);
      }

      logout(): void {
        this.isAuthenticated = false;
        this.username = '';
        sessionStorage.clear();
      }


      setUsername(username: string): void {
        this.username = username;
      }
    
      getUsername(): string {
        return this.username;
      }
}
