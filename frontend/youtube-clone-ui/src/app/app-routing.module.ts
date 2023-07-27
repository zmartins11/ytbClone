import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './components/upload-video/upload-video.component';
import { SaveVideoDetailsComponent } from './components/save-video-details/save-video-details.component';
import { AuthenticationGuard } from './authentication.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { VideoDetailComponent } from './components/video-detail/video-detail.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { HistoryComponent } from './components/history/history.component';
import { LikedVideosComponent } from './components/liked-videos/liked-videos.component';
import { FeaturedComponent } from './components/featured/featured.component';
import { RegisterComponent } from './components/register/register.component';


const routes: Routes = [
  {path : 'register', component: RegisterComponent},
    {path:'', component: HomeComponent, children: [
      {path:'subscriptions', component: SubscriptionsComponent},
      {path:'history', component: HistoryComponent},
      {path:'liked-videos', component: LikedVideosComponent},
      {path:'featured', component: FeaturedComponent}
    ]},
    {path:'', canActivate: [AuthenticationGuard], children: [
    {path:'login', component: LoginComponent},
    {path:'upload-video', component: UploadVideoComponent}, 
    {path:'save-video-details/:videoId', component: SaveVideoDetailsComponent},
    {path:'video-details/:videoId', component: VideoDetailComponent},
  ]}
  
]; 



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
