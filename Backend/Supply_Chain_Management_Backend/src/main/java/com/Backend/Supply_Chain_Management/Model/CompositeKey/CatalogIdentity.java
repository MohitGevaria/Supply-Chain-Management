package com.Backend.Supply_Chain_Management.Model.CompositeKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CatalogIdentity implements Serializable {

    //Name of the Product for which order is placed.
    @Column(length = 20)
    private String product;

    //Component name which are needed to manufacture product.
    @Column(length = 20)
    private String component;
}
