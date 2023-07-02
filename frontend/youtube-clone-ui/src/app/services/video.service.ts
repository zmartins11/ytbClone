import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadVideoResponse } from '../components/upload-video/UploadVideoResponse';

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
}
