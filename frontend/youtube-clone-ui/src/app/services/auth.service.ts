import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class AuthService implements HttpInterceptor {

    constructor() {    }

    intercept(req: HttpRequest<any>, next :  HttpHandler): Observable<HttpEvent<any>> {
        let token = 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IldqdVRhMU5ScllTdEZHV2F0RjZBUCJ9.eyJpc3MiOiJodHRwczovL2Rldi15MXV2dmNvM3RubG50c3c2LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJpWUJZV3ZzYUhLZm1WTmNXUlRrc0xOeXpzN2didzk0a0BjbGllbnRzIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo5MDkwIiwiaWF0IjoxNjg4ODU4Njc1LCJleHAiOjE2ODg5NDUwNzUsImF6cCI6ImlZQllXdnNhSEtmbVZOY1dSVGtzTE55enM3Z2J3OTRrIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.YAIGTQWOXoP9qkPuGOLFOZfKwkca04UPOyqfp4ynb94bHvHRRvMGTlxpza9kZaVFYtSE_y04cD5oCYliorELeLI_ce0bpLrpij73LvEy_DNAFjklPncY_KoLT9v1iJt_Njx3upmSFuGnINRXo0n1YFntVu-VNhP-Z17iXa64gxY8QBHVnWWzKxdYdorg4GDyp9sAvPAGmzyGAHeADj7RCdt40OooxPD6oa5Zdky9ThK9ESbWQAzqLrF82WdIqLTjjHAcvzLn8jUzRo_IYlIfpwBzLmvngrlgKkdBVTlOPm57cat8ezgJu_xEhkfjQjtSwAFGR1PsR-7msZ8JGfa5sQ';

        let jwttoken = req.clone( {
            setHeaders :  {
                Authorization : 'bearer' + token
            }
        })
        return next.handle(jwttoken);
    }
      
}