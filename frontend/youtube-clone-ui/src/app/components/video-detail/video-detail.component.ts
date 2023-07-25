import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'angular-auth-oidc-client/lib/user-data/user.service';
import { AuthService } from 'src/app/services/auth-service';
import { VideoService } from 'src/app/services/video.service';

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {


  videoId!: string;
  videoUrl! : string;
  videoTitle! : string;
  videoDescription! : string;
  tags: Array<string> = [];
  videoAvailable: boolean = false;
  likeCount: number = 0;
  dislikeCount : number = 0;
  viewCount : number = 0;
  sessionId! : string;
  userId! : string;

  showSubscribeButton : boolean = true;
  showUnSubscribeButton : boolean = false;


  constructor(private activatedRoute:  ActivatedRoute,
              private videoService: VideoService,
              private authService : AuthService) {
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.videoService.getVideo(this.videoId).subscribe(data => {
      console.log(data);
      this.videoUrl = data.videoUrl;
      this.videoTitle = data.title;
      this.videoDescription = data.description;
      this.tags = data.tags;
      this.videoAvailable = true;
      this.likeCount = data.likeCount;
      this.dislikeCount = data.dislikeCount;
      this.viewCount = data.viewCount;
      this.sessionId = sessionStorage.getItem("token") || '';
      this.userId = data.userId;

      if(this.authService.getUsername() == this.userId) {
        this.showSubscribeButton = false;
        this.showUnSubscribeButton = false;
      }
      })
      
   }

  ngOnInit(): void {
   
  }

  likeVideo() {
    console.log(this.userId);
    this.videoService.likeVideo(this.videoId).subscribe(data => {
      this.likeCount = data.likeCount;
      this.dislikeCount = data.dislikeCount;
    });
  }

  disLikeVideo() {
    this.videoService.dislikeVideo(this.videoId).subscribe(data => {
      this.likeCount = data.likeCount;
      this.dislikeCount = data.dislikeCount;
    });
  }

  subscribeToUser() {
 
   this.authService.subscribeToUser(this.userId).subscribe(data => {
      this.showUnSubscribeButton = true;
      this.showSubscribeButton = false;
   });
  }

  unSubscribeToUser() {
    this.authService.unSubscribeToUser(this.userId).subscribe(data => {
       this.showUnSubscribeButton = false;
       this.showSubscribeButton = true;
    });
   }

 /*  unSubscribeToUser() {
  } */

  /* showSubscribeButton() : boolean {
   return this.authService.getUsername() != this.userId;
  } */

/*   showUnSubscribeButton() {
  } */

}
