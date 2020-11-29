package com.Backend.Supply_Chain_Management.Model;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.RetailerIdentity;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "retailer")
@Entity
public class Retailer implements UserInter {

    @EmbeddedId
    RetailerIdentity retailerIdentity;

    @NotNull
    @Column(length = 10)
    private String id;

    @NotNull
    @Column(length = 30)
    private String email;

}
