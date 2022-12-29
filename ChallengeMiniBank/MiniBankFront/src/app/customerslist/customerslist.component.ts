import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { Customer } from '../model/Customer';
import { CustomersserviceService } from '../services/customersservice.service';

@Component({
  selector: 'app-customerslist',
  templateUrl: './customerslist.component.html',
  styleUrls: ['./customerslist.component.css']
})
export class CustomerslistComponent implements OnInit{

  customers:Customer[];

  constructor(private service:CustomersserviceService, private router:Router){};

  ngOnInit(){    
    this.service.getCustomers().subscribe(
      data=>{this.customers=data;});
  }

  NewCustomer(){
    this.router.navigate(["customersadd"])
  }

  DeleteCustomer(customer:Customer){
    if(confirm('Delete this customer?')){
    this.service.deleteCustomer(customer)
    .pipe(catchError(err=>{
      alert(err['error']);
      return throwError('')}))
    .subscribe(data=>{
      this.customers=this.customers.filter(p=>p!==customer);
      alert("Customer deleted succesfully")})}
  };

  ReturnHome(){
    this.router.navigate([""]) ;
  }

  GoToModifiyCustomer(customer:Customer):void{
    localStorage.setItem("customerId", customer.customerId.toString()) //ESTO ES IMPORTANTE; ME PERMITE ENVIARLE LOS DATOS DEL CLIENTE PREVIAMENTE SELECCIONADO EN LA TABLA
    this.router.navigate(["customersmodify"]).then(()=>{window.location.reload()});;
  }

  GoToManageAccounts(customer:Customer):void{
    localStorage.setItem("customerId", customer.customerId.toString()) //ESTO ES IMPORTANTE; ME PERMITE ENVIARLE LOS DATOS DEL CLIENTE PREVIAMENTE SELECCIONADO EN LA TABLA
    this.router.navigate(["accounts"]);
  }

}
