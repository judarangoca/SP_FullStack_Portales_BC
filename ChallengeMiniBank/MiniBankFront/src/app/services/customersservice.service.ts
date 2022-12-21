import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Customer } from '../model/Customer';
@Injectable({
  providedIn: 'root'
})

export class CustomersserviceService {
  customers:Customer[];

  constructor(private http:HttpClient) {}

  Url='http://localhost:8000/customers';

  getCustomers(){
    return this.http.get<Customer[]>(this.Url);
  }

  createNewCustomer(customer:Customer){
    return this.http.post<Customer>(this.Url,customer)
  }

  deleteCustomer(customer:Customer){
    return this.http.delete<Customer>(this.Url+"/"+customer.customerId)
  }

  getCustomerById(customerId:number){
    return this.http.get<Customer>(this.Url+"/"+customerId);
  };

  updateCustomer(customer:Customer){
    return this.http.put<Customer>(this.Url+"/"+customer.customerId, customer)
  }
}
