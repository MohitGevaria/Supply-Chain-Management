package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderDetailIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetails, OrderDetailIdentity> {

    //This method will orderDetails by given orderID.
    @Query("select or_det from OrderDetails or_det where or_det.orderDetailIdentity.orderID=:i")
    List<OrderDetails> findOrderDetails(@Param("i") String orderID);
}
