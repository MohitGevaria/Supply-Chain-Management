import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  isLoginError : boolean = false;
  constructor(private userService : UserService, private router : Router) { }

  ngOnInit(): void {
  }

  OnSubmit(username,password)
  {
    this.userService.userAuthentication (username,password).subscribe((data : any)=>{
      console.log(data);

      if(data.msg=='failure')
      {
        this.isLoginError = true;
      }
      else
      {

      var role = data.obj.id.substring(0,3);
      
      localStorage.setItem('userToken',data.obj.id);
      localStorage.setItem('userRoles',data.obj.id.substring(0,3));
      
      if(role=='man')
        this.router.navigate(['/manuf']);
      else if(role=='ret')
        this.router.navigate(['/retailer']);
      else if(role=='adm')
        this.router.navigate(['/admin']);
      else 
        this.router.navigate(['/login']);
    }
    },
    (err : HttpErrorResponse)=>{
      this.isLoginError = true;
    });
  
  }

}
