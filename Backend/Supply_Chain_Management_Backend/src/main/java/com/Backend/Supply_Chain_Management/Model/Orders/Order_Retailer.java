package com.Backend.Supply_Chain_Management.Model.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  Order_Retailer {
    
    //Id to identify each order Uniquely.
    @Column(length = 20)
    @Id
    private String orderID;

    //Retailer Who has placed order, his ID.
    @Column(length = 10)
    @NotNull
    private String retailerID;

    @Column(length = 10)
    @NotNull
    private String adminID;

    //In Rupees
    @Column(length = 15)
    @NotNull
    private long orderAmount;

    //Order Completed or Not.
    @NotNull
    private boolean isCompleted;

    //Date when Order is placed.
    @NotNull
    private Date orderDate;
}
