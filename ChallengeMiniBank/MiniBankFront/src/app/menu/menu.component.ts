import { Component } from '@angular/core';
import { TokenServiceService } from '../services/token-service.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  isLogged = false;
  isAdmin = false;
  username = "";
  roles:String[] = [];

  constructor(private tokenService: TokenServiceService) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      //console.log('token:',this.tokenService.getToken());
      //console.log('username:', this.tokenService.getUsername());
      //console.log('authorities:', this.tokenService.getAuthorities())
      
      this.isLogged = true;
      this.username = this.tokenService.getUsername();
      this.roles = this.tokenService.getAuthorities();
      this.roles.forEach(r=>{
        if(r['authority'] == "ROLE_ADMIN"){this.isAdmin=true}})
      }
    else {
      this.isLogged = false;
    }
    console.log('isLogged:',this.isLogged);
    console.log('isAdmin:',this.isAdmin);

  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

}
