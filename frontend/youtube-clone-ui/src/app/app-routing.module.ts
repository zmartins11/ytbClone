import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './components/upload-video/upload-video.component';
import { SaveVideoDetailsComponent } from './components/save-video-details/save-video-details.component';
import { AuthenticationGuard } from './authentication.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {path:'', canActivate: [AuthenticationGuard], children: [
    {path:'', component: HomeComponent},
    {path:'login', component: LoginComponent},
    {path:'upload-video', component: UploadVideoComponent}, 
    {path:'save-video-details/:videoId', component: SaveVideoDetailsComponent}
  ]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
