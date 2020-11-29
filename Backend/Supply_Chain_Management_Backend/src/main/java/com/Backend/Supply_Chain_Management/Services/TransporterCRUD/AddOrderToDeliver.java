package com.Backend.Supply_Chain_Management.Services.TransporterCRUD;

import com.Backend.Supply_Chain_Management.DAO.*;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderTransporterIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Transporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class AddOrderToDeliver {

    @Autowired
    Order_ManufacturerDAO order_manufacturerDAO;
    @Autowired
    ManufacturerDAO manufacturerDAO;
    @Autowired
    TransporterDAO transporterDAO;
    @Autowired
    Order_TransporterDAO order_transporterDAO;
    @Autowired
    Order_RetailerDAO order_retailerDAO;
    @Autowired
    AdminDAO adminDAO;

    public void add(OrderManufacturerIdentity orderManufacturerIdentity)
    {
        log.info("Placing order at transporter for delivery.");
        String adminID = order_manufacturerDAO.findAdminIdByOrderManufacturerIdentity( orderManufacturerIdentity);
        String location = manufacturerDAO.findLocationByID( orderManufacturerIdentity.getManufacturerID());
        Order_Transporter order_transporter = Order_Transporter.builder()
                .orderTransporterIdentity(OrderTransporterIdentity.builder()
                        .orderID( orderManufacturerIdentity.getOrderID())
                        //.source( location)
                        .source( orderManufacturerIdentity.getManufacturerID())
                        //.destination( adminDAO.findLocationById( adminID))
                        .destination( adminID)
                        .transporterID( transporterDAO.findIdByLocation( location))
                        .build())
                .isCompleted( false)
                .pickUp( new Date())
                .build();
        order_transporterDAO.save( order_transporter);
        log.info("Order added for delivery is : {}", order_transporter);
    }

    public void add(String orderID) {
        log.info("Placing order at transporter for delivery.");
        String adminID = order_retailerDAO.findAdminIDByOrderID( orderID);
        String location = adminDAO.findLocationById( adminID);
        Order_Transporter order_transporter = Order_Transporter.builder()
                            .orderTransporterIdentity( OrderTransporterIdentity.builder()
                                    .orderID( orderID)
                                    .transporterID( transporterDAO.findIdByLocation( location))
                                    .source( adminID)
                                    .destination( order_retailerDAO.findRetailerIDByOrderID( orderID))
                                    .build())
                            .pickUp( new Date())
                            .isCompleted( false)
                            .build();
        order_transporterDAO.save( order_transporter);
        log.info("Order placed at transporter's info : {}", order_transporter);
    }
}
