import { Component, OnInit } from '@angular/core';
import { VideoService } from 'src/app/services/video.service';

@Component({
  selector: 'app-upload-video',
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.css']
})
export class UploadVideoComponent implements OnInit {

  file: any;

  constructor(private videoService : VideoService) { }

  ngOnInit(): void {
  }

  getFile(event: any) {
    this.file = event.target.files[0];
    
    console.log("file", this.file);
  }

  uploadFile() {
    if(this.file != undefined) {
      this.videoService.uploadVideo(this.file).subscribe(data => {
        console.log("Video upload successfully");
      });
    }
  }

}
