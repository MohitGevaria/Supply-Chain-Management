package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderTransporterIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Order_TransporterDAO extends JpaRepository<Order_Transporter, OrderTransporterIdentity> {

    //This method will return all running order of given transporter.
    @Query("select ord_tra from Order_Transporter ord_tra " +
            "where ord_tra.orderTransporterIdentity.transporterID=:i and " +
            "ord_tra.isCompleted=false")
    List<Order_Transporter> findRunningOrderByTransporterID(@Param("i") String transporterID);

    //This method will return all completed order of given transporter.
    @Query("select ord_tra from Order_Transporter ord_tra " +
            "where ord_tra.orderTransporterIdentity.transporterID=:i and " +
            "ord_tra.isCompleted=true")
    List<Order_Transporter> findCompletedOrderByTransporterID(@Param("i") String transporterID);

    //This method will mark order completed.
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order_Transporter ord_tra set ord_tra.isCompleted=true " +
            "where ord_tra.orderTransporterIdentity=:i")
    int updateOrderToCompleted(@Param("i") OrderTransporterIdentity orderTransporterIdentity);

    //This method will return order is completed or not.
    @Query("select ord_tra.isCompleted from Order_Transporter ord_tra " +
            "where ord_tra.orderTransporterIdentity=:i")
    boolean isOrderCompleted(@Param("i") OrderTransporterIdentity orderTransporterIdentity);
}
