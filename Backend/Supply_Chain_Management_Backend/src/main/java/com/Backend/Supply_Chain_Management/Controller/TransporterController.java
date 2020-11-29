package com.Backend.Supply_Chain_Management.Controller;

import com.Backend.Supply_Chain_Management.Model.Orders.Order_Transporter;
import com.Backend.Supply_Chain_Management.Services.Common.CompleteOrder;
import com.Backend.Supply_Chain_Management.Services.TransporterCRUD.FetchTransporterOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/transporter")
public class TransporterController {

    @Autowired
    FetchTransporterOrder fetchTransporterOrder;
    @Autowired
    CompleteOrder completeOrder;

    @GetMapping(value = "/runningOrder")
    public List<Order_Transporter> getRunningOrder( @RequestParam("traID") String transporterID){
        log.info("Getting all orders of given transformer : {}", transporterID);
        return fetchTransporterOrder.runningOrders( transporterID);
    }

    @GetMapping(value = "/completedOrder")
    public List<Order_Transporter> getCompletedOrder( @RequestParam("traID") String transporterID){
        log.info("Getting all orders of given transformer : {}", transporterID);
        return fetchTransporterOrder.completedOrders( transporterID);
    }
    
    @PutMapping(value="/markOrderCompleted")
    public String markOrderCompleted( @RequestParam("ordID") String ordID,
                                      @RequestParam("traID") String traID,
                                      @RequestParam("source") String source,
                                      @RequestParam("destination") String destination) {
        log.info("Got info : {}, {}, {}, {}",ordID, traID, source, destination);
        return completeOrder.makeComplete(ordID, traID, source, destination);
    }
}
