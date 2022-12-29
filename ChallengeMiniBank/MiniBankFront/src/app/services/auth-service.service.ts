import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDto } from '../model/security/JwtDto';
import { LoginUser } from '../model/security/LoginUser';
import { NewUser } from '../model/security/NewUser';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  authURL = 'http://localhost:8000/auth/'

  constructor(private httpClient: HttpClient) { }

  public login(loginUser:LoginUser):Observable<any> {
    return this.httpClient.post<any>(this.authURL+"login", loginUser);
  }

  public registerNewUser(newUser:NewUser):Observable<JwtDto> {
    return this.httpClient.post<JwtDto>(this.authURL+"newUser", newUser);
  }
}
