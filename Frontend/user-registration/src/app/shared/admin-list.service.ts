import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PlaceOrder } from './place-order.model';


@Injectable({
  providedIn: 'root'
})
export class AdminListService {

  readonly rootUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  List  : any;


  getOrders()
  {

    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.get(this.rootUrl+'/api/admin/fetch?type=retailer',{ headers: reqHeader })

    
  }

  getTransporter()
  {

    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.get(this.rootUrl+'/api/admin/fetch?type=transporter',{ headers: reqHeader })
    
  }
  


  deleteUser(row) {
    console.log(row);
    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.post(this.rootUrl+'/api/retailer/placeOrder?retID=ret1111111&amount='
                                                         ,this.List,{ headers: reqHeader });
  }
}

