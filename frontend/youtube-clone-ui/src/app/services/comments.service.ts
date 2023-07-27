import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentDto } from '../comment-dto';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient : HttpClient) { }

  postComment(commentDto : any, videoId: string):Observable<any> {
   return this.httpClient.post<any>("http://localhost:9090/api/videos/" + videoId + "/comment", commentDto);
  }

  getAllComments(videoId: string): Observable<Array<CommentDto>> {
   return this.httpClient.get<CommentDto[]>("http://localhost:9090/api/videos/" + videoId + "/comment")
  }

  //    @PostMapping("/{videoId}/deleteComment/{commentId}")
  deleteComment(commentDto : any):Observable<any> {
    return this.httpClient.post<any>("http://localhost:9090/api/videos/deleteComment", commentDto);
  }

}
