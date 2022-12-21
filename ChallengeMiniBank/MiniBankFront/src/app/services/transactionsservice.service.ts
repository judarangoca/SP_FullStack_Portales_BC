import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transaction } from '../model/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionsserviceService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8000/transactions"

  getTransactionsByAccountId(accountId:number){
    return this.http.get<Transaction[]>(this.Url+"/accountId"+accountId);
  }

  createNewDeposit(transaction:Transaction){
    return this.http.post(this.Url+"/deposit",transaction);
  }

  createNewWithdraw(transaction:Transaction){
    return this.http.post(this.Url+"/withdraw", transaction);
  }

  createNewTransfer(transaction:Transaction){
    return this.http.post(this.Url+"/transfer", transaction);
  }
}
