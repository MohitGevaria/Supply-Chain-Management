package com.Backend.Supply_Chain_Management.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private T obj;
    private String Msg;
}
