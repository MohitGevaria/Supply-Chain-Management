package com.Backend.Supply_Chain_Management.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Login {

    @JsonProperty("id")
    @Column(length = 10)
    @Nullable
    private String id;

    @Id
    @NotNull
    @Column(length = 30)
    private String email;

    @NotNull
    @Column(length = 20)
    private String passwd;
}
