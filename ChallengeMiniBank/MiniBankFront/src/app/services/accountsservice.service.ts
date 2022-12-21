import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../model/Account';

@Injectable({
  providedIn: 'root'
})
export class AccountsserviceService {

  constructor(private http:HttpClient){
  };

  Url='http://localhost:8000/accounts';
  
  getAccountsByCustomerId(customerId:number):Observable<Account[]> {
    return this.http.get<Account[]>(this.Url+"/customerId"+customerId)
  }

  createNewAccount(account:Account){
    return this.http.post(this.Url+"/",account);
  }

  deleteAccountByAccountId(account:Account){
    return this.http.delete<Account>(this.Url+"/accountId"+account.accountId)
  };

  getAllAccounts(){
    return this.http.get<Account[]>(this.Url+"/")
  };

  GetAccountByAccountId(accountId:number){
    return this.http.get<Account>(this.Url+"/accountId"+accountId);
  }

  ModifyAccount(account:Account){
    return this.http.put<Account>(this.Url+"/accountId"+account.accountId, account)
  }

}
