package com.Backend.Supply_Chain_Management.Services.RetailerCRUD;

import com.Backend.Supply_Chain_Management.DAO.OrderDetailsDAO;
import com.Backend.Supply_Chain_Management.DAO.Order_RetailerDAO;
import com.Backend.Supply_Chain_Management.Model.Orders.OrderDetails;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Retailer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FetchRetailerOrder {

    @Autowired
    Order_RetailerDAO order_retailerDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    //This utility method will retrieve all running orders of retailer.
    public List<Order_Retailer> fetchRunning(String retailerID)
    {
        List<Order_Retailer> orders = order_retailerDAO.findAllRunningOrderOfRetailer( retailerID);
        log.info("Running Orders of  retailer : {} are : {}",retailerID, orders);
        return orders;
    }

    //This utility method will retrieve all completed orders of retailer.
    public List<Order_Retailer> fetchCompleted(String retailerID)
    {
        List<Order_Retailer> orders = order_retailerDAO.findAllCompletedOrderOfRetailer( retailerID);
        log.info("Completed Orders of  retailer : {} are : {}",retailerID, orders);
        return orders;
    }

    //This utility method will retrieve details for given orders.
    public List<OrderDetails> fetchOrderDetails(String orderID)
    {
        List<OrderDetails> orderDetails = orderDetailsDAO.findOrderDetails( orderID);
        return  orderDetails;
    }
}
