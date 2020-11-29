package com.Backend.Supply_Chain_Management.Model.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WarehouseComponent {

    @Id
    @Column(length = 20)
    private String componentName;

    @NotNull
    @Column(length = 9)
    private int availableCount;


}
