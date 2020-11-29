package com.Backend.Supply_Chain_Management.Model;

import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="admin")
@Entity
public class Admin implements UserInter {

    @Id
    @JsonProperty("id")
    @Column(length = 10)
    private String id;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    @Column(unique=true,length = 30)
    private String email;

    @NotNull
    @Column(length = 20, unique = true)
    private String location;
}
