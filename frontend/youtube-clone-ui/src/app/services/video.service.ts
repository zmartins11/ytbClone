import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { UploadVideoResponse } from '../components/upload-video/UploadVideoResponse';
import { VideoDto } from '../video-dto';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  private getRequestHeaders(): HttpHeaders {
    const sessionId = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('sessionId', sessionId || '');
    console.log(sessionId);
    return headers;
  }

  uploadVideo(file: File): Observable<UploadVideoResponse> {
    const headers = this.getRequestHeaders();
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.httpClient.post<UploadVideoResponse>("http://localhost:9090/api/videos", formData, {headers : headers});
  }

  uploadThumbnail(file: File, videoId : string): Observable<string> {
    const formData = new FormData();
    const headers = this.getRequestHeaders();
    formData.append('file', file, file.name);
    formData.append('videoId', videoId);

    return this.httpClient.post("http://localhost:9090/api/videos/thumbnail", formData, {
      responseType: 'text',
      headers : headers
    });
  }

  getVideo(videoId : string) : Observable<VideoDto> {
    const headers = this.getRequestHeaders();
   return this.httpClient.get<VideoDto>("http://localhost:9090/api/videos/" + videoId, {headers : headers})
  }

  saveVideo(videoMetadata : VideoDto) : Observable<VideoDto> {
    const headers = this.getRequestHeaders();
    return this.httpClient.put<VideoDto>("http://localhost:9090/api/videos", videoMetadata, {headers : headers});
  }

  getAllVideos(): Observable<Array<VideoDto>> {
    const headers = this.getRequestHeaders();
    return this.httpClient.get<Array<VideoDto>>("http://localhost:9090/api/videos", {headers : headers});
  }

  likeVideo(videoId: string):Observable<VideoDto> {
    const headers = this.getRequestHeaders();
    return this.httpClient.post<VideoDto>("http://localhost:9090/api/videos/"+videoId+"/like",null, {headers : headers})
  }

  dislikeVideo(videoId: string):Observable<VideoDto> {
    const headers = this.getRequestHeaders();
    return this.httpClient.post<VideoDto>("http://localhost:9090/api/videos/"+videoId+"/dislike",null, {headers : headers})
  }

  
}
