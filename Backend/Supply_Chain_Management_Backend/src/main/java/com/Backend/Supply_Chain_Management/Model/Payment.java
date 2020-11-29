package com.Backend.Supply_Chain_Management.Model;

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
public class Payment {

    @Column(length = 20)
    @Id
    private String paymentID;

    @Column(length = 20)
    @NotNull
    private String orderID;

    //Payment Given from.
    @Column(length = 10)
    @NotNull
    private String fromID;

    //Payment Given to.
    @Column(length = 10)
    @NotNull
    private String toID;

    @NotNull
    @Column(length = 15)
    private long amount;

    private Date date;

    //this is for payment of respective order is completed or not(v2)
    @NotNull
    private boolean isCompleted;
}
