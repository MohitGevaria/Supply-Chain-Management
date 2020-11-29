import { Component, OnInit, ViewChild ,AfterViewInit } from '@angular/core';
import { ManufService } from 'src/app/shared/manuf.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

import { MatDialogConfig, MatDialog, MatDialogRef }  from '@angular/material/dialog';



@Component({
  selector: 'app-completed',
  templateUrl: './completed.component.html',
  styleUrls: ['./completed.component.css']
})
export class CompletedComponent implements OnInit {

  public array = [];
  

  constructor(private service: ManufService, public dialog: MatDialog,) { }



  listData : MatTableDataSource<any>;
  displayedColumns : string[] = ['orderID','component','orderAmount','actions',];
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;
  searchKey: string;

  ngOnInit(): void {

    
    this.service.completedOrders().subscribe((data: any)=>
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


}
