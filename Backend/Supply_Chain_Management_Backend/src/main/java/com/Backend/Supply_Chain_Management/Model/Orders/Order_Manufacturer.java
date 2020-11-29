package com.Backend.Supply_Chain_Management.Model.Orders;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderManufacturerIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order_Manufacturer {

    @EmbeddedId
    private OrderManufacturerIdentity orderManufacturerIdentity;

    //Admin who has given Order to Manufacturer to manufacture component.
    @Column(length = 10)
    private String adminID;

    //Number of Pieces of Component should be produced.
    @Column(length = 9)
    @NotNull
    private int amount;

    //Date of Completion of Given Order.
    private Date expectedCompletion;

    //Order is Completed or Not.
    @NotNull
    private boolean isCompleted;
}
