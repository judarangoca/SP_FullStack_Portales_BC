import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';
import { CustomersserviceService } from '../services/customersservice.service';

@Component({
  selector: 'app-customersadd',
  templateUrl: './customersadd.component.html',
  styleUrls: ['./customersadd.component.css']
})
export class CustomersaddComponent implements OnInit {

	customerId: number = null; 
	identificationType: String = ""
	identificationNumber: String = "";
	firstName: String = "";
	lastName: String = "";
	email: String = "";
	birthDate: Date = new Date('1900-01-01');
	phoneNumber: String = "";
	creationDate:Date = new Date(); //this returns the current date
  modifcationDate: Date = new Date(); //this returns the current date
  userModificator:String = "admin";

  constructor(private router:Router, private service:CustomersserviceService){
  };

  ngOnInit(): void {
  }

  SaveNewCust(){
    let cust = new Customer(this.customerId, 
      this.identificationType,
      this.identificationNumber,
      this.firstName,
      this.lastName,
      this.email,
      this.birthDate,
      this.phoneNumber,
      this.creationDate,
      this.modifcationDate,
      this.userModificator);
    
    if(confirm('¿Desear registrar el nuevo cliente?')){
    this.service.createNewCustomer(cust)
    .subscribe(data=>{alert("Agregado con exito...");
    this.router.navigate(["customerslist"]);});
    }
  };

}
