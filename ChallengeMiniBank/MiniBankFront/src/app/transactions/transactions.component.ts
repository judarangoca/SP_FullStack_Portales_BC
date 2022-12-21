import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from '../model/Account';
import { Transaction } from '../model/Transaction';
import { AccountsserviceService } from '../services/accountsservice.service';
import { TransactionsserviceService } from '../services/transactionsservice.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  
  constructor(
    private service:TransactionsserviceService,
    private accountService:AccountsserviceService,
    private router:Router,
    public formBuilder:FormBuilder){  
    }

  transactions:Transaction[];
  currentAccount: Account = new Account();
  currentAccountId = +localStorage.getItem("accountId");
  newTransaction:Transaction;
  accounts:Account[];

  
  formNewTransaction = new FormGroup(
    {
    typeOfTransaction: new FormControl('', Validators.required),
    transaccionValue: new FormControl(null,Validators.required),
    originAccountId: new FormControl(null),
    destinationAccountId: new FormControl(null,),
    });
  
  ListingTransactionsByAccountId():void{

    //Algunos valores para interpolar el Html.
    this.accountService.GetAccountByAccountId(this.currentAccountId).
    subscribe(res=>this.currentAccount=res);

    this.service.getTransactionsByAccountId(this.currentAccountId).
    subscribe(data=>{this.transactions=data});

  };

  ngOnInit(): void {
    this.ListingTransactionsByAccountId();
  };

  cardForNewTransaction=false;
  GenerateNewTransaction(){
    this.accountService.getAllAccounts().subscribe(data=>this.accounts = data);
    this.cardForNewTransaction=true;
  };

  saveNewTransaction(){
    this.newTransaction = new Transaction();
    this.newTransaction.typeOfTransaction = this.formNewTransaction.value.typeOfTransaction;
    this.newTransaction.transactionValue = this.formNewTransaction.value.transaccionValue;
    this.newTransaction.transactionDate = new Date();

      if(confirm("¿Esta seguro de generar el movimiento?")) {
        switch(this.formNewTransaction.value.typeOfTransaction){
        case "Deposit":
          this.formNewTransaction.get('destinationAccountId').addValidators(Validators.required)
          this.newTransaction.destinationAccountId = this.formNewTransaction.value.destinationAccountId;
          this.newTransaction.originAccountId = null;

          this.service.createNewDeposit(this.newTransaction).
          subscribe(data=>{
            alert(`Deposito Exitoso`);
            this.myFunc();
            this.newTransaction = new Transaction();
            this.router.navigate(['transactions']).then(()=>{window.location.reload()});
          })
        break;

        case "Withdraw":
          this.newTransaction.destinationAccountId = null;
          this.formNewTransaction.get('originAccountId').addValidators(Validators.required)
          this.newTransaction.originAccountId = this.formNewTransaction.value.originAccountId;
          
          this.service.createNewWithdraw(this.newTransaction).
          subscribe(data=>{
            alert(`Retiro Exitoso`);
            this.myFunc();
            this.newTransaction = new Transaction();
            this.router.navigate(['transactions']).then(()=>{window.location.reload()});
          })
          break;

        case "Transfer":
          this.newTransaction.destinationAccountId = this.formNewTransaction.value.destinationAccountId;
          this.newTransaction.originAccountId = this.formNewTransaction.value.originAccountId;

          this.service.createNewTransfer(this.newTransaction).
          subscribe(data=>{
            alert(`Transferencia Existosa`);
            this.myFunc();
            this.newTransaction = new Transaction();
            this.router.navigate(['transactions']).then(()=>{window.location.reload()});
          })

          break
        }

    }
    
  }

  myFunc():void{
    this.formNewTransaction.reset()
  }

  ReturnHome(){
    this.router.navigate([''])
  }

  ReturnAccounts(){
    this.router.navigate(['accounts'])
  }

}
