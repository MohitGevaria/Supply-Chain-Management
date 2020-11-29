import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PlaceOrder } from './place-order.model';



@Injectable({
  providedIn: 'root'
})
export class OrderListService {
  readonly rootUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  List  : any;

  form : FormGroup = new FormGroup({
    $key : new FormControl(null),
    name : new FormControl('', Validators.required),
    qty : new FormControl('', Validators.required),
    city : new FormControl('', Validators.required),
    model : new FormControl('', Validators.required),
  }); 

  initializeFormGroup() {
    this.form.setValue({
      $key: null,
      name: '',
      qty: '',
      model: '',
      city: '',
    });
  }

  

  getOrders()
  {
    var id = localStorage.getItem("userToken");

    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.get(this.rootUrl+'/api/retailer/runningOrder?retID='+id,{ headers: reqHeader })
  }
    
  insertOrder(order)
  {
    var placeOrder : PlaceOrder =
    {
      count : order.qty,
      product : order.name 
    }

    this.List = [
      {"product": order.name , "count" : order.qty,  },
    ];
    
    var id = localStorage.getItem("userToken");
    
    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.post(this.rootUrl+'/api/retailer/placeOrder?retID='+id+'&amount='+
                                                         order.qty*1000 + '&location=Delhi',this.List,{ headers: reqHeader });
    
  }




  deleteEmployee($key: string) {
    //this.employeeList.remove($key);
  }
}
