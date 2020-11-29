package com.Backend.Supply_Chain_Management.Model.Orders;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderTransporterIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order_Transporter {

    @EmbeddedId
    OrderTransporterIdentity orderTransporterIdentity;

    //Date when order should be pick up.
    private Date pickUp;

    //Order is Completed or Not.
    @NotNull
    private boolean isCompleted;
}
