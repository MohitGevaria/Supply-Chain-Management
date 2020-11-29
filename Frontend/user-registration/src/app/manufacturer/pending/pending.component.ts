import { Component, OnInit, ViewChild ,AfterViewInit } from '@angular/core';
import { ManufService } from 'src/app/shared/manuf.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { NotificationService } from 'src/app/shared/notification.service';

import { MatDialogConfig, MatDialog, MatDialogRef }  from '@angular/material/dialog';



@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
  styleUrls: ['./pending.component.css']
})
export class PendingComponent implements OnInit {

  constructor(private service: ManufService, public dialog: MatDialog, private notificationService : NotificationService,) { }


  public array = [];
  listData : MatTableDataSource<any>;
  displayedColumns : string[] = ['orderID','Component','orderAmount','actions',];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;
  searchKey: string;


  ngOnInit(): void {
    
    this.service.pendingOrders().subscribe((data: any)=>
    {
      
      this.array =  data;
      this.listData = new MatTableDataSource(this.array);
    });
    

    this.listData = new MatTableDataSource(this.array);
      

  }

  ngAfterViewInit (){
    this.listData.sort = this.sort; 
    this.listData.paginator = this.paginator;
  }

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  onComplete(id){

    if(confirm('Add it to the completed list?')){
      

      this.service.updateOrders(id).subscribe((data: any) => {
      console.log(data);

    });

    this.notificationService.warn('! Order Completed');
    window.location.reload();
    }
  }


}
