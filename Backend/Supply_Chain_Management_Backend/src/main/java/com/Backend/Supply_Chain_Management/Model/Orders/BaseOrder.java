package com.Backend.Supply_Chain_Management.Model.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOrder {

    @NotNull
    private String product;

    @NotNull
    private int count;
}
