package com.example.customerserviceapp.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date date;
    private String address;
    private String postCode;
}
