package com.example.customerserviceapp.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Data;

@Data
public class CustomerRequestDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Date date;
    @NotNull
    private String address;
    @NotNull
    private String postCode;
}
