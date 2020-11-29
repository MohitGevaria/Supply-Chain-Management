package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Order_ManufacturerDAO extends JpaRepository<Order_Manufacturer, OrderManufacturerIdentity> {

    //This method will find all running order of given manufacturers by UniqueID.
    @Query("select ord_man from Order_Manufacturer ord_man where" +
            " ord_man.orderManufacturerIdentity.manufacturerID=:i and ord_man.isCompleted=false")
    List<Order_Manufacturer> findRunningOrderByManufacturerID( @Param("i") String manufacturerID);

    //This method will find all completed order of given manufacturers by UniqueID.
    @Query("select ord_man from Order_Manufacturer ord_man where" +
            " ord_man.orderManufacturerIdentity.manufacturerID=:i and ord_man.isCompleted=true")
    List<Order_Manufacturer> findCompletedOrderByManufacturerID( @Param("i") String manufacturerID);

    //This method will find order given to manufacturer by manufacturerIdentity.
    @Query("select ord_man from Order_Manufacturer ord_man where " +
            "ord_man.orderManufacturerIdentity=:i")
    Order_Manufacturer findOrderManufacturerByOrderManufacturerIdentity(@Param("i") OrderManufacturerIdentity orderManufacturerIdentity);

    //This method will mark order completed.
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order_Manufacturer ord_man set ord_man.isCompleted=true " +
            "where ord_man.orderManufacturerIdentity=:i")
    int updateOrderToCompleted(@Param("i") OrderManufacturerIdentity orderManufacturerIdentity);

    //This method will find admin by manufacturerIdentity.
    @Query("select ord_man.adminID from Order_Manufacturer ord_man where " +
            "ord_man.orderManufacturerIdentity=:i")
    String findAdminIdByOrderManufacturerIdentity( @Param("i") OrderManufacturerIdentity orderManufacturerIdentity);

    //This method will return component name by orderID and manufacturerID.
    @Query("select ord_man.orderManufacturerIdentity.componentName from Order_Manufacturer ord_man " +
            "where ord_man.orderManufacturerIdentity.orderID=:o and " +
            "ord_man.orderManufacturerIdentity.manufacturerID=:m")
    String findComponentByOrderIDAndManufacturerID( @Param("o")String orderID,
                                                    @Param("m")String manufacturerID);

    //This method will return component count given to manufacture
    //to manufacturer by orderID and manufacturerID.
    @Query("select ord_man.amount from Order_Manufacturer ord_man where " +
            "ord_man.orderManufacturerIdentity.orderID=:o and " +
            "ord_man.orderManufacturerIdentity.manufacturerID=:m")
    int findComponentCountByOrderIDAndManufacturerID( @Param("o")String orderID,
                                                    @Param("m")String manufacturerID);

}
