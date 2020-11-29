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
public class OrderManufacturerIdentity implements Serializable {

    @Column(length = 20)
    @NotNull
    private String orderID;

    //Id of the Manufacturer whom order given
    @Column(length = 10)
    @NotNull
    private String manufacturerID;

    //For which component manufacturing order is given.
    @Column(length = 20)
    @NotNull
    private String componentName;
}
