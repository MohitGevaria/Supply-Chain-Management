package com.Backend.Supply_Chain_Management.Model;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.ManufacturerIdentity;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="manufacturer")
@Entity
public class Manufacturer implements UserInter {

    @EmbeddedId
    private ManufacturerIdentity manufacturerIdentity;

    @NotNull
    @Column(length = 10)
    private String id;

    @NotNull
    @Column(length = 30)
    private String email;
}
