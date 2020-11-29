import { Component, OnInit } from '@angular/core';
import { OrderListService } from 'src/app/shared/order-list.service';
import { OrderListComponent } from '../order-list/order-list.component';
import { MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from 'src/app/shared/notification.service';



@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  constructor(public service : OrderListService, public dialogRef: MatDialogRef<OrderListComponent>
    , public notificationService : NotificationService  
    ) { }


  city = [
    { id : 1, value : 'Delhi' },
    { id : 2, value : 'Ahmedabad' },
    { id : 3, value : 'Kolkata' },
  ];

  ngOnInit(): void {
  }

  onClear() {
    this.service.form.reset();
    this.service.initializeFormGroup();
  
  }

  onSubmit() {
      
    if (this.service.form.valid) {
      
      
      this.service.insertOrder(this.service.form.value).subscribe((data:any )=> {
        console.log("Success");
      });
      
      this.service.form.reset();
      this.service.initializeFormGroup();
      this.notificationService.success(':: Submitted successfully');
      window.location.reload();
      this.onClose();
    }

  }
  
  onClose() {
    this.service.form.reset();
    this.service.initializeFormGroup();
    this.dialogRef.close();
  }


  
}
