package com.Backend.Supply_Chain_Management.Model.Orders;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderDetailIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @EmbeddedId
    private OrderDetailIdentity orderDetailIdentity;

    //Total number of Given Model ordered.
    @Column(length = 10)
    @NotNull
    private int count;

}
