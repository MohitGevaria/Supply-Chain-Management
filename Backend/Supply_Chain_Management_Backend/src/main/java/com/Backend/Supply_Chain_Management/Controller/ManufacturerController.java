package com.Backend.Supply_Chain_Management.Controller;

import com.Backend.Supply_Chain_Management.Model.Orders.Order_Manufacturer;
import com.Backend.Supply_Chain_Management.Services.Common.CompleteOrder;
import com.Backend.Supply_Chain_Management.Services.ManufacturerCRUD.FetchManufacturerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@RequestMapping("/api/manufacturer")
@CrossOrigin
@RestController
public class ManufacturerController {

    @Autowired
    FetchManufacturerOrder fetchManufacturerOrder;
    @Autowired
    CompleteOrder completeOrder;

    //This method will used for retrieving all running orders.
    @GetMapping(value="/getRunningOrders")
    public List<Order_Manufacturer> getRunningOrder(@RequestParam("manufacturerID") String manufacturerID) {
        log.info("Getting all Running order of Manufacturer : {}", manufacturerID);
        return fetchManufacturerOrder.runningOrders( manufacturerID);
    }

    //This method will used for retrieving all completed orders.
    @GetMapping(value="/getCompletedOrders")
    public List<Order_Manufacturer> getCompletedOrder(@RequestParam("manufacturerID") String manufacturerID) {
        log.info("Getting all Completed order of Manufacturer : {}", manufacturerID);
        return fetchManufacturerOrder.completedOrders( manufacturerID);
    }

    //This method will used to mark order completed.
    @PutMapping(value = "/markOrderCompleted")
    public String markOrderCompleted(@RequestParam("ordID")String orderID,
                                     @RequestParam("manID") String manufactureID,
                                     @RequestParam("component") String component)
    {
        log.info("Completing order with orderID : {} given to manufacturer : {} to" +
                " manufacture component : {}", orderID, manufactureID, component);
        return completeOrder.makeComplete(orderID, manufactureID, component);
    }
}
