import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})

export class CustomersComponent {

  constructor(private router:Router){ };

  ListCustomers(){
    this.router.navigate(["customerslist"])
  }

  NewCustomer(){
    this.router.navigate(["customersadd"])
  }

  ReturnHome(){
    this.router.navigate([""]);
  }

}
