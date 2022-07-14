package com.example.customerserviceapp.dto.mapper;

import com.example.customerserviceapp.dto.CustomerRequestDto;
import com.example.customerserviceapp.dto.CustomerResponseDto;
import com.example.customerserviceapp.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toModel(CustomerRequestDto requestDto) {
        Customer customer = new Customer();
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customer.setAddress(requestDto.getAddress());
        customer.setPostCode(requestDto.getPostCode());
        return customer;
    }

    public CustomerResponseDto toResponseDto(Customer customer) {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(customer.getId());
        responseDto.setFirstName(customer.getFirstName());
        responseDto.setLastName(customer.getLastName());
        responseDto.setDate(customer.getDate());
        responseDto.setAddress(customer.getAddress());
        responseDto.setPostCode(customer.getPostCode());
        return responseDto;
    }
}
