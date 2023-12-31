import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseStatus } from '../responseStatus';
import { VideoDto } from '../video-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  isAuthenticated: boolean = false;
  private username: string = '';
  private userId: string = '';
  private sessionId: string = '';

  constructor(private httpClient: HttpClient) { }

  private getRequestHeaders(): HttpHeaders {
    const sessionId = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('sessionId', sessionId || '');
    console.log(sessionId);
    return headers;
  }

  login(username: string, password: string): Observable<ResponseStatus> {
    const body = {
      username: username,
      password: password
    };
    return this.httpClient.post<ResponseStatus>("http://localhost:9090/api/login", body);
  }

  loginUser(username: string, password: string) {
    this.httpClient.get<ResponseStatus>("http://localhost:9090/api/login").subscribe(data => {
      this.sessionId = data.sessionId;
      this.userId = data.userId;
    })
  }

  logout(): void {
    this.isAuthenticated = false;
    this.username = '';
    sessionStorage.clear();
  }

  subscribeToUser(userId: string): Observable<boolean> {
    const headers = this.getRequestHeaders();
    return this.httpClient.post<boolean>("http://localhost:9090/api/user/subscribe/" + userId, null,{headers : headers});
  }

  unSubscribeToUser(userId: string): Observable<boolean> {
    const headers = this.getRequestHeaders();
    return this.httpClient.post<boolean>("http://localhost:9090/api/user/unSubscribe/" + userId, null,{headers : headers});
  }

  userHistory(userId: string): Observable<Array<VideoDto>> {
    const headers = this.getRequestHeaders();
    return this.httpClient.get<Array<VideoDto>>("http://localhost:9090/api/user/" + userId + "/history", {headers : headers});
  }




  setUsername(username: string): void {
    this.username = username;
  }

  getUsername(): string {
    return this.username;
  }

  getUserId(): string {
    return this.userId;
  }

  setUserId(userId: string): void {
    this.userId = userId;
  }
}
