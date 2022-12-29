import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { TokenServiceService } from '../services/token-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = "MiniBank App"

  isLogged = false;
  username = '';


  constructor(private router:Router, private tokenService:TokenServiceService){};

  ListUsers(){
    this.router.navigate(["userslist"]);
  }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.username = this.tokenService.getUsername();
    } else {
      this.isLogged = false;
      this.username = '';
    }
  }
}
