import { Routes } from "@angular/router";
import { pathToFileURL } from 'url';
import { AddUserComponent } from './admin-panel/add-user/add-user.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { TransportListComponent } from './admin-panel/transport-list/transport-list.component';
import { UserListComponent } from './admin-panel/user-list/user-list.component';
import { AuthGuard } from './auth/auth.guard';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { CompletedComponent } from './manufacturer/completed/completed.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { PendingComponent } from './manufacturer/pending/pending.component';
import { RetailerComponent } from './retailer/retailer.component';
import { SignInComponent } from './user/sign-in/sign-in.component';
import { SignUpComponent } from './user/sign-up/sign-up.component';
import { UserComponent } from './user/user.component';

export const appRoutes : Routes = [
    
    { path : 'retailer', component: RetailerComponent,canActivate: [AuthGuard] , data: { roles: ['ret']   }},
    { path : 'home' , component  : HomeComponent , canActivate: [AuthGuard]  },
    
    { path : 'forbidden', component: ForbiddenComponent, canActivate: [AuthGuard] },
    
    {
        path  : 'login', component : UserComponent, 
        children : [{ path : '', component: SignInComponent}]
    },

    {   path : 'admin', component : AdminPanelComponent,   canActivate: [AuthGuard] , data: { roles: ['adm'] },
        children :  [
                        { path : 'adduser' , component : AddUserComponent},
                        { path : 'userlist' , component : UserListComponent},
                        { path : 'transportlist' , component : TransportListComponent},
                        
                    ]
    }, 

    {   
        
        path : 'manuf', component : ManufacturerComponent, canActivate: [AuthGuard] , data: { roles: ['man'] },
        children :  [
                        { path : 'pending' , component : PendingComponent},
                        { path : 'completed' , component : CompletedComponent},
                        
                    ]
    }, 
    
    {
        path : '', redirectTo:'/login', pathMatch:'full'  
    },


    
    
    
];