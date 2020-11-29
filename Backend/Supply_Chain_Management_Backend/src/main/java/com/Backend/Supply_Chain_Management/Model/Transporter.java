package com.Backend.Supply_Chain_Management.Model;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.TransporterIdentity;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Transporter implements UserInter {

    @EmbeddedId
    TransporterIdentity transporterIdentity;

    //Id of Transporter.
    @NotNull
    @Column(length = 10)
    private String id;

    //e-mail of Transporter.
    @NotNull
    @Column(length = 30)
    private String email;
}
