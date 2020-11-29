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
public class TransporterIdentity implements Serializable {

    //Name of the Transporter.
    @NotNull
    @Column(length = 20)
    private String name;

    //Location of Transporter Office.
    @NotNull
    @Column(length = 20)
    private String location;
}
