import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';
import { CustomersserviceService } from '../services/customersservice.service';

@Component({
  selector: 'app-customersmodify',
  templateUrl: './customersmodify.component.html',
  styleUrls: ['./customersmodify.component.css']
})
export class CustomersmodifyComponent implements OnInit{

  constructor(private service:CustomersserviceService, private router:Router){};

  customer:Customer;

  EditCustomer(){ //En esta funcion haremos el mapeo del objeto a los campos
    
    let customerId=localStorage.getItem("customerId");

    this.service.getCustomerById(+customerId). //El signo + permite castear el string a numbe
    subscribe(data=>{this.customer=data});
  };

  UpdateCustomer(customer:Customer){
    if(confirm('Update customer information?')){
    this.service.updateCustomer(customer).
    subscribe(data=>{
      this.customer=data;
      alert("Customer Updated Correctly ...");
    });
    this.router.navigate(['customerslist']).then(()=>{window.location.reload()});
    }
  }

  ReturnToCustomersListing(){
    this.router.navigate(["customerslist"])
  }

  ngOnInit(){
    this.EditCustomer();
  };
}
