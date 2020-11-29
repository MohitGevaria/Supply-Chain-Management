package com.Backend.Supply_Chain_Management.Model.Orders;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.CatalogIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Catalog {

    @EmbeddedId
    private CatalogIdentity catalogIdentity;

    //Total number of component needed to manufacture product.
    private int count;
}
