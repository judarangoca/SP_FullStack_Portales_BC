import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CustomersComponent } from './customers/customers.component';
import { CustomerslistComponent } from './customerslist/customerslist.component';
import { CustomersaddComponent } from './customersadd/customersadd.component';
import { CustomersmodifyComponent } from './customersmodify/customersmodify.component';
import { CustomersserviceService } from './services/customersservice.service';
import { HttpClientModule } from '@angular/common/http';
import { UserslistComponent } from './userslist/userslist.component';
import { UsersserviceService } from './services/usersservice.service';
import { AccountsComponent } from './accounts/accounts.component';
import { AccountsserviceService } from './services/accountsservice.service';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransactionsserviceService } from './services/transactionsservice.service';
import { ContactComponent } from './contact/contact/contact.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { MenuComponent } from './menu/menu.component';
import { interceptorProvider, TokenInterceptorService } from './interceptors/token-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CustomersComponent,
    CustomerslistComponent,
    CustomersaddComponent,
    CustomersmodifyComponent,
    UserslistComponent,
    AccountsComponent,
    TransactionsComponent,
    ContactComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent
  ],
  
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    UsersserviceService,
    CustomersserviceService,
    AccountsserviceService,
    TransactionsserviceService,
    interceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
