import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title = "APLICATIVO MINIBANK"

  constructor(private router:Router){};

  ListUsers(){
    this.router.navigate(["userslist"]);
  }
}
