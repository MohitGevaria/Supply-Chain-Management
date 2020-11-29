import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ManufService {

  readonly rootUrl = 'http://localhost:8080';
  
  constructor(private http: HttpClient) { }



  form : FormGroup = new FormGroup({
    $key : new FormControl(null),
    name : new FormControl('', Validators.required),
    qty : new FormControl('', Validators.required),
    components : new FormControl('', Validators.required),
    model : new FormControl('', Validators.required),
  }); 

  initializeFormGroup() {
    this.form.setValue({
      $key: null,
      name: '',
      qty: '',
      model: '',
      components: '',
    });
  }


  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
 }

  pendingOrders()
  {
    console.log("pending");
    var id = localStorage.getItem("userToken");
    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.get(this.rootUrl+'/api/manufacturer/getRunningOrders'+'?manufacturerID='+id,{ headers: reqHeader })
    

    
  }
  
  completedOrders()
  {
    var id = localStorage.getItem("userToken");

    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.get(this.rootUrl+'/api/manufacturer/getCompletedOrders'+'?manufacturerID='+id,{ headers: reqHeader })
  }

  updateOrders(id)
  {
    console.log(id);
    var reqHeader = new HttpHeaders({ 'Contents-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    console.log(this.rootUrl+'/api/manufacturer/markOrderCompleted?ordID='+id.orderManufacturerIdentity.orderID +' &manID='+localStorage.getItem('userToken')+ 
    '&component='+id.orderManufacturerIdentity.componentName);
    return this.http.put(this.rootUrl+'/api/manufacturer/markOrderCompleted?ordID='+id.orderManufacturerIdentity.orderID +'&manID='+localStorage.getItem('userToken')+ 
                              '&component='+id.orderManufacturerIdentity.componentName,{'responseType': 'text'} ,{ headers: reqHeader }).pipe
    
    (catchError(this.handleError) );
    

  }   
  
}
