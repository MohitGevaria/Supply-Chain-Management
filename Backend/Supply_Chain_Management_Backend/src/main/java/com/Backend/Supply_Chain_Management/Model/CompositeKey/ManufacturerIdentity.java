package com.Backend.Supply_Chain_Management.Model.CompositeKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class ManufacturerIdentity implements Serializable {

    //Component name Which manufacturer producing.
    @NotNull
    @Column(length = 20)
    private String component;

    //Name of the Manufacturer
    @NotNull
    @Column(length = 20)
    private String name;

    //Location of Manufacturer production site.
    @NotNull
    @Column(length = 20)
    private String location;
}
