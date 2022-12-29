import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewUser } from 'src/app/model/security/NewUser';
import { User } from 'src/app/model/User';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { TokenServiceService } from 'src/app/services/token-service.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  
  newUser: NewUser;

  username:String='';
  password:String='';
  name:String='';
  surname:String='';
  email:String='';

  isLogged = false;
  isAdmin = false;
  isSignUpFail = false;

  constructor(
    private tokenService: TokenServiceService,
    private authService: AuthServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }

    this.tokenService.getAuthorities().forEach(element => {
      if(element['authority']=="ROLE_ADMIN"){this.isAdmin=true}
    }); 
  }


  onRegister(): void {
    this.newUser = new NewUser(this.username, this.password, this.name, this.surname, this.email);
    this.authService.registerNewUser(this.newUser).subscribe(
    data => {
      alert('New User Registered Correctly');
      this.router.navigate(['/login']);
    },
    err => {
      this.isSignUpFail=true;
      console.log('isSignUpFail',this.isSignUpFail)
      console.log(err);
      console.log(err.error.message);
    });
    }
  }

