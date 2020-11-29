package com.Backend.Supply_Chain_Management.Services.TransporterCRUD;

import com.Backend.Supply_Chain_Management.DAO.Order_TransporterDAO;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Transporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FetchTransporterOrder {

    @Autowired
    Order_TransporterDAO order_transporterDAO;

    public List<Order_Transporter> runningOrders(String transporterID)
    {
        List<Order_Transporter> orders = order_transporterDAO.findRunningOrderByTransporterID(transporterID);
        log.info("Fetched Orders : {}", orders);
        return orders;
    }

    public List<Order_Transporter> completedOrders(String transporterID)
    {
        List<Order_Transporter> orders = order_transporterDAO.findCompletedOrderByTransporterID(transporterID);
        log.info("Fetched Orders : {}", orders);
        return orders;
    }
}
