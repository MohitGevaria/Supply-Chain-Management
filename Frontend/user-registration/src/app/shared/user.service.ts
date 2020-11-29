import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { User } from './user.model';
import { Adduser } from './Adduser.model';
import { Login } from './login.model';

@Injectable()
export class UserService {
  readonly rootUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  registerUser(user : User){
    const body: User = {
      UserName: user.UserName,
      Password: user.Password,
      Email: user.Email,
      FirstName: user.FirstName,
      Location: user.Location
    }
    const body1: Adduser = {
      name: user.FirstName,
      type: user.UserName,
      component : null,
      location: user.Location,
  
    }
   

    console.log(user);
    var reqHeader = new HttpHeaders({'No-Auth':'True', responseType: 'text'});
    return this.http.post(this.rootUrl + '/api/admin/add' , body1, { headers: reqHeader } );
    
  }

  

  
  userAuthentication(userName, password) {
    //var data = "username=" + userName + "&password=" + password ;
    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    var login : Login =
    {
      email : userName,
      passwd : password,
      id : null
    }
    console.log("API called");
    return this.http.post('http://localhost:8080/api/login',login, { headers: reqHeader });

  }

  getUserClaims(){
   return  this.http.get(this.rootUrl+'/api/GetUserClaims');
  }

  getAllRoles() {
    var reqHeader = new HttpHeaders({ 'No-Auth': 'True' });
    return this.http.get(this.rootUrl + '/api/GetAllRoles', { headers: reqHeader });
  }

  roleMatch(allowedRoles): boolean {
    var isMatch = false;
    var userRoles: string = (localStorage.getItem('userRoles'));
    allowedRoles.forEach(element => {
      if (userRoles.indexOf(element) > -1) {
        isMatch = true;
        return false;
      }
    });
    return isMatch;

  }
}