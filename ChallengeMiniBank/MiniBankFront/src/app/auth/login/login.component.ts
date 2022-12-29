import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtDto } from 'src/app/model/security/JwtDto';
import { LoginUser } from 'src/app/model/security/LoginUser';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { TokenServiceService } from 'src/app/services/token-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLogged=false;
  isLoginFail=false;
  loginUser:LoginUser;
  username:String;
  password:String;
  roles:String[] = [];

  constructor(
    private tokenService:TokenServiceService,
    private authService:AuthServiceService,
    private router:Router
     ){}

  ngOnInit(): void {
    if(this.tokenService.getToken()) //verificamos que exista un token
    {
      this.isLogged=true;
      this.isLoginFail=false;
      this.roles=this.tokenService.getAuthorities();
    }
  }

  onLogin():void{
    this.loginUser = new LoginUser(this.username, this.password);
    this.authService.login(this.loginUser).subscribe(
      data=>{
        console.log(data);
        this.isLogged=true;
        this.isLoginFail=false;

        this.tokenService.setToken(data.token);
        this.tokenService.setUsername(data.username);
        this.tokenService.setAuthorities(data.authorities);

        this.roles=data.authorities;
        this.router.navigate(['login']).then(()=>{window.location.reload});
      },

      err=>{
        this.isLogged=false;
        this.isLoginFail=true;
        console.log(err);
        console.log(err.error.message);}
      )

    this.router.navigate(['']).then(()=>{window.location.reload})
    
  }

  goToLogin(){}

}
