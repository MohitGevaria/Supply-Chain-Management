package com.Backend.Supply_Chain_Management.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@Builder
public class User {

    @Nullable
    @Column(length = 20)
    private String name;

    @Nullable
    @Column(length = 15)
    private String type;

    @Nullable
    @Column(length = 20)
    private String location;

    @Nullable
    @JsonProperty("component")
    private String component;
}
