import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentDto } from 'src/app/comment-dto';
import { AuthService } from 'src/app/services/auth-service';
import { CommentsService } from 'src/app/services/comments.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input()
  videoId : string = "";
  commentsForm: FormGroup; 
  commentsDto : CommentDto[] = [];

  constructor(private authService: AuthService,
            private commentService : CommentsService,
            private matSnackBar: MatSnackBar) {
    this.commentsForm = new FormGroup({
      comment : new FormControl('comment'),
    });
   }

  ngOnInit(): void {
    this.getAllComments();
  }

  postComment() {
    const comment = this.commentsForm.get('comment')?.value;

    const commentDto = {
      "commentText" : comment,
      "authorId": this.authService.getUsername()
    }

    this.commentService.postComment(commentDto, this.videoId).subscribe(()=> {
      this.matSnackBar.open("Comment Posted Successfully", "OK");

      this.commentsForm.get('comment')?.reset();

      this.getAllComments();
    })
  }

  getAllComments() {
    this.commentService.getAllComments(this.videoId).subscribe(data =>{
      this.commentsDto = data;
    });
  }

}
