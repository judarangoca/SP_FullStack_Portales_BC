import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { Account } from '../model/Account';
import { Customer } from '../model/Customer';
import { AccountsserviceService } from '../services/accountsservice.service';
import { CustomersserviceService } from '../services/customersservice.service';
import { TokenServiceService } from '../services/token-service.service';

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
  
  isAdmin = false;

  constructor(
    private router:Router,
    private service:AccountsserviceService,
    private customerService:CustomersserviceService,
    private tokenService:TokenServiceService){};
  
  ngOnInit(): void {
    this.ListingAccountsByCustomerId();
  }

  ListingAccountsByCustomerId(){

    this.tokenService.getAuthorities().forEach(element => {
      if(element['authority']=="ROLE_ADMIN"){this.isAdmin=true}})

    let customerId=localStorage.getItem("customerId");
    //Obtenemos el clienteActual
    this.customerService.getCustomerById(+customerId).pipe().
    subscribe(data=>this.currentCustomer=data);

    this.service.getAccountsByCustomerId(+customerId).pipe().
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
      alert("Account Created Correctly");
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
    pipe(catchError(err=>{
      alert(err['error']);
      // this.modifiedAccount=new Account();
      // this.router.navigate(['accounts']).then(()=>{window.location.reload()});
      return throwError(err['error']);})).
    subscribe(data=>{ 
      alert("Account modified corectly");
      this.modifiedAccount=new Account();
      this.router.navigate(['accounts']).then(()=>{
        window.location.reload()});});
  }

  DeleteAccount(account:Account){
    if(confirm('Delete This Account?')){
      this.service.deleteAccountByAccountId(account).
      pipe(catchError(err=>{throw new Error(err['error'])})).
      subscribe(data=>{
        this.accounts=this.accounts.filter(acc=>acc!==account);
        alert("Account deleted correctly");
        this.router.navigate(['accounts']).then(()=>{
        window.location.reload()});
      });
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

