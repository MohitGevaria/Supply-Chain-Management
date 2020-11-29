import { Component, OnInit, ViewChild ,AfterViewInit } from '@angular/core';
import { OrderListService } from 'src/app/shared/order-list.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { PlaceOrderComponent } from '../place-order/place-order.component';
import { MatDialogConfig, MatDialog, MatDialogRef }  from '@angular/material/dialog';



@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  public array = [];

  constructor(private service: OrderListService, public dialog: MatDialog,) { }

  listData : MatTableDataSource<any>;
  displayedColumns : string[] = ['orderID','orderAmount','orderDate','actions',];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;
  searchKey: string;

  ngOnInit() {
    
    this.service.getOrders().subscribe((data: any)=>
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

  onCreate() {
    this.service.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(PlaceOrderComponent,dialogConfig);
  }


}



