package com.Backend.Supply_Chain_Management.Services.Common;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.*;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderTransporterIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Transporter;
import com.Backend.Supply_Chain_Management.Model.Orders.WarehouseComponent;
import com.Backend.Supply_Chain_Management.Services.TransporterCRUD.AddOrderToDeliver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CompleteOrder {

    @Autowired
    Order_ManufacturerDAO order_manufacturerDAO;
    @Autowired
    AddOrderToDeliver addOrderToDeliver;
    @Autowired
    Order_TransporterDAO order_transporterDAO;
    @Autowired
    WarehouseComponentDAO warehouseComponentDAO;
    @Autowired
    Order_RetailerDAO order_retailerDAO;

    //This method will call by manufacturer after completing manufacturing of
    //component.
    public String makeComplete(String orderID, String manufacturerID, String Component)
    {
        OrderManufacturerIdentity orderManufacturerIdentity = OrderManufacturerIdentity.builder()
                                                                .orderID( orderID)
                                                                .manufacturerID( manufacturerID)
                                                                .componentName( Component)
                                                                .build();
        //log.info("Order details are : {}", order_manufacturerDAO.findById( orderManufacturerIdentity));
        order_manufacturerDAO.updateOrderToCompleted(orderManufacturerIdentity);
        log.info("Order Completion done.");
        addOrderToDeliver.add( orderManufacturerIdentity);
        return Constants.SUCCESS;
    }

    //This method will call by transporter when transporter has
    //completed delivery of his order.
    public String makeComplete(String orderID, String transporterID,
                               String sourceID, String destinationID)
    {
        if(destinationID.substring(0,3).equals("adm"))
        {
            //Transporter has delivered his order from manufacture to admin.
            //So component will be added at wareHouse.(In v1 warehouse should be at Delhi.)
            //In v2 warehouse should be at every admin location.
            OrderTransporterIdentity orderTransporterIdentity = OrderTransporterIdentity.builder()
                                                                .orderID( orderID)
                                                                .transporterID( transporterID)
                                                                .source( sourceID)
                                                                .destination( destinationID)
                                                                .build();
            boolean isCompleted = order_transporterDAO.isOrderCompleted( orderTransporterIdentity);
            if(!isCompleted)
            {
                order_transporterDAO.updateOrderToCompleted(orderTransporterIdentity);
                log.info("Order completion done by {} for info : {}", transporterID,
                        orderTransporterIdentity);
                String component =
                        order_manufacturerDAO.findComponentByOrderIDAndManufacturerID( orderID, sourceID);
                int count =
                        order_manufacturerDAO.findComponentCountByOrderIDAndManufacturerID( orderID, sourceID);
                int componentPresent = warehouseComponentDAO.findByComponentName( component);
                WarehouseComponent warehouseComponent = WarehouseComponent.builder()
                        .componentName( component)
                        .availableCount( componentPresent + count)
                        .build();
                warehouseComponentDAO.save( warehouseComponent);
                log.info("Component : {} count also increased at warehouse by : {}", component, count);
                return Constants.SUCCESS;
            }
            else
            {
                log.info("order is already completed");
                return "Order Already Completed.";
            }
        }
        else if(destinationID.substring(0,3).equals("ret")) {
            OrderTransporterIdentity orderTransporterIdentity = OrderTransporterIdentity.builder()
                    .orderID( orderID)
                    .transporterID( transporterID)
                    .source( sourceID)
                    .destination( destinationID)
                    .build();
            order_transporterDAO.updateOrderToCompleted(orderTransporterIdentity);
            log.info("Order completion done by {} for info : {}", transporterID,
                    orderTransporterIdentity);
            order_retailerDAO.updateOrderToCompleted( orderID);
            log.info("Order also completed at Retailer.");
            return Constants.SUCCESS;
        }
        else {
            log.info("Not valid information.");
            return Constants.FAILURE;
        }
    }

    //This method will call by Admin when order given by retailer is completed.
    public String makeComplete( String orderID)
    {
        boolean isCompleted = order_retailerDAO.isOrderCompleted( orderID);
        if(!isCompleted)
        {
            order_retailerDAO.updateOrderToCompleted( orderID);
            log.info("Order : {} Completed at Admin.", orderID);
            addOrderToDeliver.add( orderID);
            return Constants.SUCCESS;
        }
        else
        {
            log.info("Order is Already Completed.");
            return "Order Already Completed.";
        }
    }
}
