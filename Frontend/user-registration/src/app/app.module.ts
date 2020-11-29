import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from "@angular/router";


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './shared/user.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { UserComponent } from './user/user.component'
import { SignUpComponent } from './user/sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './user/sign-in/sign-in.component';
import { appRoutes } from './routes';
import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.interceptor';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { OrderListComponent } from './retailer/order-list/order-list.component';
import { RetailerComponent } from './retailer/retailer.component';
import { MaterialModule } from './material/material.module';
import { PlaceOrderComponent } from './retailer/place-order/place-order.component';
import { ReactiveFormsModule } from '@angular/forms';
import { OrderListService } from './shared/order-list.service';
import { AddUserComponent } from './admin-panel/add-user/add-user.component';
import { UserListComponent } from './admin-panel/user-list/user-list.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { CompletedComponent } from './manufacturer/completed/completed.component';
import { PendingComponent } from './manufacturer/pending/pending.component';
import { TransportListComponent } from './admin-panel/transport-list/transport-list.component';
 

  

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    UserComponent,
    HomeComponent,
    SignInComponent,
    AdminPanelComponent,  
    ForbiddenComponent,
    OrderListComponent,
    RetailerComponent,
    PlaceOrderComponent,
    AddUserComponent,
    UserListComponent,
    ManufacturerComponent,
    CompletedComponent,
    PendingComponent,
    TransportListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes),
    MaterialModule,
    ReactiveFormsModule,
  ],
  providers: [UserService, AuthGuard,
  {
      provide : HTTP_INTERCEPTORS,
      useClass : AuthInterceptor,
      multi : true
  },OrderListService,
],
  bootstrap: [AppComponent],
  entryComponents:[PlaceOrderComponent]

})
export class AppModule { }
