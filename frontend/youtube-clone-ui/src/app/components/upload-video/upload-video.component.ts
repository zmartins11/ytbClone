import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VideoService } from 'src/app/services/video.service';

@Component({
  selector: 'app-upload-video',
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.css']
})
export class UploadVideoComponent implements OnInit {

  file: any;

  constructor(private videoService : VideoService,
    private router: Router) { }

  ngOnInit(): void {
  }

  getFile(event: any) {
    this.file = event.target.files[0];
    
    console.log("file", this.file);
  }

  uploadFile() {
    if(this.file != undefined) {
      this.videoService.uploadVideo(this.file).subscribe(data => {
        this.router.navigateByUrl("/save-video-details/" + data.videoId)
      });
    }
  }

}
