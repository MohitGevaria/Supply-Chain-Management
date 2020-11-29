import { Component, OnInit, ViewChild ,AfterViewInit } from '@angular/core';
import { AdminListService } from 'src/app/shared/admin-list.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { NotificationService } from 'src/app/shared/notification.service';


import { MatDialogConfig, MatDialog, MatDialogRef }  from '@angular/material/dialog';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  public array = [];
  constructor(private service: AdminListService, public dialog: MatDialog, private notificationService : NotificationService,) { }

  listData : MatTableDataSource<any>;
  displayedColumns : string[] = ['id','name','location','email','actions',];
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

  onDelete(id){
    if(confirm('Are you sure to delete this record ?')){
      console.log(id.orderID);
    
      this.service.deleteUser(id.orderID).subscribe((data: any) => {
        console.log(data);
        
    });

    this.notificationService.warn('! Deleted successfully');
    window.location.reload();
    }
      
  }

}




