package com.Backend.Supply_Chain_Management.Services.ManufacturerCRUD;

import com.Backend.Supply_Chain_Management.DAO.Order_ManufacturerDAO;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Manufacturer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FetchManufacturerOrder {

    @Autowired
    Order_ManufacturerDAO order_manufacturerDAO;

    public List<Order_Manufacturer> runningOrders(String manufacturerID)
    {
        List<Order_Manufacturer> orders = order_manufacturerDAO.findRunningOrderByManufacturerID( manufacturerID);
        return orders;
    }

    public List<Order_Manufacturer> completedOrders(String manufacturerID)
    {
        List<Order_Manufacturer> orders = order_manufacturerDAO.findCompletedOrderByManufacturerID( manufacturerID);
        return orders;
    }
}
