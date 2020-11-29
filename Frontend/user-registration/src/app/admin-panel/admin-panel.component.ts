import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
  }
  Logout() {
    localStorage.removeItem('userToken');
    this.router.navigate(['/login']);
  }

  
}
