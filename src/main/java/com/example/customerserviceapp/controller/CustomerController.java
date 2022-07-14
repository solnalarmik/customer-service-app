package com.example.customerserviceapp.controller;

import com.example.customerserviceapp.dto.CustomerRequestDto;
import com.example.customerserviceapp.dto.CustomerResponseDto;
import com.example.customerserviceapp.dto.mapper.CustomerMapper;
import com.example.customerserviceapp.exception.DataProcessingException;
import com.example.customerserviceapp.model.Customer;
import com.example.customerserviceapp.service.CustomerService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/inject")
    public void inject() {
        Customer customer = new Customer();
        customer.setFirstName("Yuliia");
        customer.setLastName("Tkachenko");
        customer.setPostCode("SE9 1XA");
        //customer.setDate(new Date());
        customerService.save(customer);
        Customer customer1 = new Customer();
        customer1.setFirstName("Oleh");
        customer1.setLastName("Tkachenko");
        customer1.setPostCode("SE9 1XA");
        // customer1.setDate(new Date());
        Customer customer2 = new Customer();
        customer2.setFirstName("Masha");
        customer2.setLastName("Suchinina");
        customer2.setPostCode("08636");
        //customer2.setDate(new Date());
        Customer customer3 = new Customer();
        customer3.setFirstName("Tanya");
        customer3.setLastName("Such");
        customer3.setPostCode("05037");
        // customer3.setDate(new Date());
        customerService.save(customer);
        customerService.save(customer1);
        customerService.save(customer2);
        customerService.save(customer3);
        System.out.println("findAll:");
        customerService.findAll().forEach(c -> System.out.println("findAll: " + c));
        System.out.println("findById : " + customerService.findById(1L));
    }

    @PostMapping
    public CustomerResponseDto create(@RequestBody @Valid CustomerRequestDto requestDto) {
        Customer customer = customerService.save(customerMapper.toModel(requestDto));
        return customerMapper.toResponseDto(customer);
    }

    @GetMapping("/all")
    public List<CustomerResponseDto> findAll() {
        return customerService.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<CustomerResponseDto> findAll(@RequestParam Map<String, String> params) {
        return customerService.findAll(params)
                .stream()
                .map(customerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponseDto findById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerMapper
                .toResponseDto(customerService.findById(id)
                        .orElseThrow(() -> new DataProcessingException("Customer with Id " + id
                                + " wasn't found in DB")));
        return customerResponseDto;
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CustomerRequestDto customerRequestDto) {
        Customer customer = customerMapper.toModel(customerRequestDto);
        customerService.update(id, customer);
        return customerMapper.toResponseDto(customerService.update(id, customer));

    }


}
