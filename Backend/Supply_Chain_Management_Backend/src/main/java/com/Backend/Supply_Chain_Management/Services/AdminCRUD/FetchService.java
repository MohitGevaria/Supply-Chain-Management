package com.Backend.Supply_Chain_Management.Services.AdminCRUD;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.*;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Retailer;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FetchService {

    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Autowired
    private RetailerDAO retailerDAO;
    @Autowired
    private TransporterDAO transporterDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private Order_RetailerDAO order_retailerDAO;

    //This will return all manufacturer/admin/retailer/transporter of System based on type.
    public List<UserInter> fetchUser(String type)
    {
        List<UserInter> fetchedUser = null;
        if(type.equals( Constants.Manufacturer_type))
        {
            fetchedUser = manufacturerDAO.getAllManufacturer();
        }
        else if( type.equals( Constants.Admin_type)) {
            fetchedUser = adminDAO.getAllAdmin();
        }
        else if( type.equals( Constants.Retailer_type))
        {
            fetchedUser = retailerDAO.getAllRetailer();
        }
        else if( type.equals( Constants.Transporter_type))
        {
            fetchedUser = transporterDAO.getAllTransporter();
        }
        return fetchedUser;
    }

    //This will return all running orders given to admin by retailer.
    public List<Order_Retailer> runningOrders(String adminID)
    {
        return order_retailerDAO.findAllRunningOrderOfAdmin( adminID);
    }

    //This will return all completed orders given to admin by retailer.
    public List<Order_Retailer> completedOrders(String adminID)
    {
        return order_retailerDAO.findAllCompletedOrderOfAdmin( adminID);
    }
}
