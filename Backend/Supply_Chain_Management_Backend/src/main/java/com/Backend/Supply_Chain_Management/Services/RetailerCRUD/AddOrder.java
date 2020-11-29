package com.Backend.Supply_Chain_Management.Services.RetailerCRUD;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.AdminDAO;
import com.Backend.Supply_Chain_Management.DAO.CatalogDAO;
import com.Backend.Supply_Chain_Management.DAO.OrderDetailsDAO;
import com.Backend.Supply_Chain_Management.DAO.Order_RetailerDAO;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderDetailIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.BaseOrder;
import com.Backend.Supply_Chain_Management.Model.Orders.Catalog;
import com.Backend.Supply_Chain_Management.Model.Orders.OrderDetails;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Retailer;
import com.Backend.Supply_Chain_Management.Services.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AddOrder {

    @Autowired
    Generator generator;
    @Autowired
    CatalogDAO catalogDAO;
    @Autowired
    OrderDetailsDAO orderDetailsDAO;
    @Autowired
    AddOrderUtility addOrderUtility;
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    Order_RetailerDAO order_retailerDAO;

    public String add(String retailerID, String location, long orderAmount, List<BaseOrder> orders)
    {
        String response = "";
        if(!orders.isEmpty())
        {
            String orderID = generator.generateID(20);
            String adminID = adminDAO.findIdByLocation( location);
            Order_Retailer order_retailer = Order_Retailer.builder()
                                    .orderID( orderID)
                                    .retailerID( retailerID)
                                    .adminID( adminID)
                                    .orderAmount( orderAmount)
                                    .orderDate( new Date())
                                    .isCompleted( false)
                                    .build();
            order_retailerDAO.save( order_retailer);
            log.info("Order successfully placed by ID:{}", orderID);
            for(int i = 0; i < orders.size(); i++)
            {
                log.info("Adding list of orders in OrderDetails table.");
                String product = orders.get(i).getProduct();
                int count = orders.get(i).getCount();
                OrderDetails orderDetail = OrderDetails.builder()
                                            .orderDetailIdentity(OrderDetailIdentity.builder()
                                                    .orderID( orderID)
                                                    .product( product)
                                                    .build())
                                            .count( count)
                                            .build();
                orderDetailsDAO.save( orderDetail);
                List<Catalog> catalogs = catalogDAO.findByProductName(product);
                addOrderUtility.orderToManufacturer( orderID, catalogs, count);
            }
            response = Constants.SUCCESS;
        }
        return response;
    }
}
