import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UsersserviceService {

  users:User[];

  Url='http://localhost:8000/users';

  constructor(private http:HttpClient) { };

  getCustomers():Observable<any>{
    return this.http.get<any>(this.Url);
  }
}
