import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from '../model/Account';
import { Customer } from '../model/Customer';
import { AccountsserviceService } from '../services/accountsservice.service';
import { CustomersserviceService } from '../services/customersservice.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {


  newAccount:Account = new Account(); //instanciamos una nueva clase para crear una nueva cuenta
  modifiedAccount:Account; //dclaramos un nuevo objeto para ser modificado
  accounts:Account[];
  cardForNewAccount:boolean=false; //Declaramos una variable para habilitar un cuadro con la creacion de nueva cuenta
  cardForEditAccount:boolean=false;
  currentCustomer:Customer = new Customer(null,'','','','','',null,'',null,null,'')
  

  constructor(private router:Router, private service:AccountsserviceService, private customerService:CustomersserviceService){};
  
  ngOnInit(): void {
    this.ListingAccountsByCustomerId();
  }

  ListingAccountsByCustomerId(){
    let customerId=localStorage.getItem("customerId");
    //Obtenemos el clienteActual
   
    this.customerService.getCustomerById(+customerId).pipe().
    subscribe(data=>this.currentCustomer=data);

    this.service.getAccountsByCustomerId(+customerId).
    subscribe(data=>{this.accounts=data});
  }

  ShowNewAccountForm(){
    let customerId=localStorage.getItem("customerId");
    this.newAccount.customerId = +customerId //el signo + me deja hacer un cast
    this.cardForNewAccount=true;
    this.cardForEditAccount=false;
  };

  SaveNewAccount(account:Account){
    this.service.createNewAccount(account)
    .subscribe(data=>{
      alert("Cuenta Agregada con Exito");
      this.router.navigate(['accounts']).then(()=>{
        window.location.reload();
      });
    });
  };
  
  ShowEditAccountForm(account:Account ){
    this.cardForEditAccount = true;
    this.cardForNewAccount = false;

    this.modifiedAccount = account;
  }

  SaveEditAccount(account:Account){
    this.service.ModifyAccount(account).
    subscribe(data=>{alert("Se modificó la cuenta correctamente")});
    this.modifiedAccount=new Account();
    this.router.navigate(['accounts']).then(()=>{
      window.location.reload()});
  }

  DeleteAccount(account:Account){
    if(confirm('¿Desea Eliminar la Cuenta?')){
      this.service.deleteAccountByAccountId(account)
      .subscribe(data=>{
        this.accounts=this.accounts.filter(acc=>acc!==account)
      });
      
      alert("Cuenta eliminada");

      this.router.navigate(['accounts']).then(()=>{
      window.location.reload()});
    }
  };

  GoToTransactions(account:Account){
    localStorage.setItem("accountId", account.accountId.toString()) //ESTO ES IMPORTANTE; ME PERMITE ENVIARLE LOS DATOS DEL CLIENTE PREVIAMENTE SELECCIONADO EN LA TABLA
    localStorage.setItem("accountNumber", account.accountNumber.toString());
    this.router.navigate(['transactions'])
  }

  ReturnHome(){
    this.router.navigate([''])
  };

  ReturnToCustomersListing(){
    this.router.navigate(["customerslist"])
  };

};

