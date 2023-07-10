import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from 'src/app/services/video.service';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { VideoPlayerComponent } from '../video-player/video-player.component';
import { VideoDto } from 'src/app/video-dto';
import { textChangeRangeIsUnchanged } from 'typescript';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent implements OnInit {

  saveVideoDetailsForm : FormGroup;
  title: FormControl = new FormControl('');
  description: FormControl = new FormControl('');
  videoStatus: FormControl = new FormControl('');

  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes = [13, 188];
  tags : string [] =[];
  selectedFile!: File;
  selectedFileName = '';
  videoId = '';
  fileSelected = false;
  videoUrl! : string;
  thumbnailUrl! :string;

   
  constructor(private activatedRoute : ActivatedRoute,
     private videoService : VideoService,
     private matSnackBar: MatSnackBar,
     private http : HttpClient) {
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.videoService.getVideo(this.videoId).subscribe(data => {
    this.videoUrl = data.videoUrl;
    this.thumbnailUrl = data.thumbnailUrl;
    })
    this.saveVideoDetailsForm = new FormGroup({
      title : this.title,
      description : this.description,
      videoStatus : this.videoStatus,
    })
   }

  ngOnInit(): void {
  }


  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }
  onFileSelected(event: Event) {
    // @ts-ignore
    this.selectedFile = event.target.files[0];
    this.selectedFileName = this.selectedFile.name;
    this.fileSelected = true;
  }
 
  onUpload() {
    this.videoService.uploadThumbnail(this.selectedFile, this.videoId).subscribe(data => {
      console.log(data);
      //show an upload success notification
      this.matSnackBar.open('Thumbnail Upload Successful', 'OK');
    })
  }

  saveVideo() {
    //call the video service to make a http call to our backend
    const videoMetadata : VideoDto = {
      "id": this.videoId,
      "title" : this.saveVideoDetailsForm.get('title')?.value,
      "description" : this.saveVideoDetailsForm.get('description')?.value,
      "tags": this.tags,
      "videoStatus": this.saveVideoDetailsForm.get("videoStatus")?.value,
      "videoUrl" : this.videoUrl,
      "thumbnailUrl": this.thumbnailUrl
    }
    this.videoService.saveVideo(videoMetadata).subscribe(data => {
      this.matSnackBar.open("Video Metadata Updated successfully", "OK")
    })
  }



}
