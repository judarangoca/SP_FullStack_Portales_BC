import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { CustomersComponent } from './customers/customers.component';
import { CustomersaddComponent } from './customersadd/customersadd.component';
import { CustomerslistComponent } from './customerslist/customerslist.component';
import { CustomersmodifyComponent } from './customersmodify/customersmodify.component';
import { HomeComponent } from './home/home.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { UserslistComponent } from './userslist/userslist.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'customers', component:CustomersComponent},
  {path:'customerslist',component:CustomerslistComponent},
  {path:'customersadd',component:CustomersaddComponent},
  {path:'customersmodify',component:CustomersmodifyComponent},
  {path:'userslist',component:UserslistComponent},
  {path:'accounts',component:AccountsComponent},
  {path:'transactions',component:TransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
