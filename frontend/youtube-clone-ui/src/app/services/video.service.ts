import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadVideoResponse } from '../components/upload-video/UploadVideoResponse';
import { VideoDto } from '../video-dto';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File): Observable<UploadVideoResponse> {
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.httpClient.post<UploadVideoResponse>("http://localhost:9090/api/videos", formData);
  }

  uploadThumbnail(file: File, videoId : string): Observable<string> {
    const formData = new FormData();
    formData.append('file', file, file.name);
    formData.append('videoId', videoId);

    return this.httpClient.post("http://localhost:9090/api/videos/thumbnail", formData, {
      responseType: 'text'
    });
  }

  getVideo(videoId : string) : Observable<VideoDto> {
   return this.httpClient.get<VideoDto>("http://localhost:9090/api/videos/" + videoId)
  }
}
