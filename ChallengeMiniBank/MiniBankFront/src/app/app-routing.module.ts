import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ContactComponent } from './contact/contact/contact.component';
import { CustomersComponent } from './customers/customers.component';
import { CustomersaddComponent } from './customersadd/customersadd.component';
import { CustomerslistComponent } from './customerslist/customerslist.component';
import { CustomersmodifyComponent } from './customersmodify/customersmodify.component';
import { HomeComponent } from './home/home.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { UserslistComponent } from './userslist/userslist.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'home', component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'newuser',component:RegisterComponent},
  {path:'register',component:RegisterComponent},
  {path:'customers', component:CustomersComponent},
  {path:'customerslist',component:CustomerslistComponent},
  {path:'customersadd',component:CustomersaddComponent},
  {path:'customersmodify',component:CustomersmodifyComponent},
  {path:'userslist',component:UserslistComponent},
  {path:'accounts',component:AccountsComponent},
  {path:'transactions',component:TransactionsComponent},
  {path:'contact',component:ContactComponent},
  { path: "**", redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
