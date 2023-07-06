import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-y1uvvco3tnlntsw6.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'v7QJIQzJArCARDlhCVCI1mi2K8kFukXY',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
