package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.Orders.Order_Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Order_RetailerDAO extends JpaRepository<Order_Retailer, String> {

    //This method will fetch all Order of given retailer.
    @Query("select ord from Order_Retailer ord where ord.retailerID=:i and " +
            "ord.isCompleted=false")
    List<Order_Retailer> findAllRunningOrderOfRetailer( @Param("i") String retailerID);

    //This method will fetch all Order of given retailer.
    @Query("select ord from Order_Retailer ord where ord.retailerID=:i and " +
            "ord.isCompleted=true")
    List<Order_Retailer> findAllCompletedOrderOfRetailer( @Param("i") String retailerID);

    //This method will give adminID by orderID.
    @Query("select ord.adminID from Order_Retailer ord where ord.orderID=:i")
    String findAdminIDByOrderID(@Param("i") String orderID);

    //This method will give retailerID by orderID.
    @Query("select ord.retailerID from Order_Retailer ord where ord.orderID=:i")
    String findRetailerIDByOrderID(@Param("i") String orderID);

    //This method will mark order completed.
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order_Retailer ord_ret set ord_ret.isCompleted=true " +
            "where ord_ret.orderID=:i")
    int updateOrderToCompleted(@Param("i") String orderID);

    //This method will return all orders of given admin.
    @Query("select ord from Order_Retailer ord where ord.adminID=:i " +
            "and ord.isCompleted=false")
    List<Order_Retailer> findAllRunningOrderOfAdmin( @Param("i") String adminID);

    //This method will return all orders of given admin.
    @Query("select ord from Order_Retailer ord where ord.adminID=:i " +
            "and ord.isCompleted=true")
    List<Order_Retailer> findAllCompletedOrderOfAdmin( @Param("i") String adminID);

    //This will check given order is completed or not.
    @Query("select ord.isCompleted from Order_Retailer ord where " +
            "ord.orderID=:i")
    Boolean isOrderCompleted( @Param("i") String orderID);
}
