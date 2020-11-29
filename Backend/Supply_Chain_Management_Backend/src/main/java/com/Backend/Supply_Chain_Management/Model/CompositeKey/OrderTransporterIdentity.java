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
public class OrderTransporterIdentity implements Serializable {

    @NotNull
    @Column(length = 20)
    private String orderID;

    //Id of the Transporter whom order given.
    @NotNull
    @Column(length = 10)
    private String transporterID;

    //Source location to Pick up Order.
    @NotNull
    @Column(length = 20)
    private String source;

    //Destination location to Drop Order.
    @NotNull
    @Column(length = 20)
    private String destination;
}
