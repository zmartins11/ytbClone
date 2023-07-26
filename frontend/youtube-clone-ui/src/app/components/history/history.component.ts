import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service';
import { VideoDto } from 'src/app/video-dto';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  username!: string;
  videoHistory: Array<VideoDto> = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    this.authService.userHistory(this.username).subscribe(data =>{
      this.videoHistory = data;
    })
  }

}
