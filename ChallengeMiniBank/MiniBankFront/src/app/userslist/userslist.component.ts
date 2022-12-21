import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { UsersserviceService } from '../services/usersservice.service';

@Component({
  selector: 'app-userslist',
  templateUrl: './userslist.component.html',
  styleUrls: ['./userslist.component.css']
})
export class UserslistComponent implements OnInit{

  constructor(private service:UsersserviceService, router:Router){};

  users:User[];

  ngOnInit(): void {
    console.log("El método OnInit se inició: ok")
    
    this.service.getCustomers().subscribe(
      data=>{this.users=data;});
      
  };

}
