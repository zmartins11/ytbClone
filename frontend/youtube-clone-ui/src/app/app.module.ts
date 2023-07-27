import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UploadVideoComponent } from './components/upload-video/upload-video.component';
import { HeaderComponent } from './components/header/header.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SaveVideoDetailsComponent } from './components/save-video-details/save-video-details.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { MatSelectModule} from '@angular/material/select';
import { MatOptionModule} from '@angular/material/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { VgCoreModule} from '@videogular/ngx-videogular/core';
import { VgControlsModule} from '@videogular/ngx-videogular/controls';
import { VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import { VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import { VideoPlayerComponent } from './components/video-player/video-player.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RequestInterceptor } from './request.interceptor';
import { VideoDetailComponent } from './components/video-detail/video-detail.component';
import { HistoryComponent } from './components/history/history.component';
import { LikedVideosComponent } from './components/liked-videos/liked-videos.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { FeaturedComponent } from './components/featured/featured.component';
import { VideoCardComponent } from './components/video-card/video-card.component';
import { MatCardModule } from '@angular/material/card';
import { CommentsComponent } from './components/comments/comments.component';
import { RegisterComponent } from './components/register/register.component';



@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    SaveVideoDetailsComponent,
    LoginComponent,
    HomeComponent,
    VideoDetailComponent,
    HistoryComponent,
    LikedVideosComponent,
    SidebarComponent,
    SubscriptionsComponent,
    FeaturedComponent,
    VideoCardComponent,
    CommentsComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule, 
    FlexLayoutModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatOptionModule,
    MatChipsModule,
    MatSnackBarModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule,
    FormsModule,
    VideoPlayerComponent,
    MatSidenavModule,
    MatListModule,
    MatCardModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }