package com.Backend.Supply_Chain_Management.Model.CompositeKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailIdentity implements Serializable {

    @Column(length = 20)
    @NotNull
    private String orderID;

    //Name of the product which Retailer has ordered.
    @Column(length = 20)
    @NotNull
    private String product;
}
