package com.example.customerserviceapp.service;

import com.example.customerserviceapp.dto.mapper.CustomerMapper;
import com.example.customerserviceapp.exception.DataProcessingException;
import com.example.customerserviceapp.model.Customer;
import com.example.customerserviceapp.repository.CustomerRepository;
import com.example.customerserviceapp.repository.specification.CustomerSpecificationManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerSpecificationManager customerSpecificationManager;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSpecificationManager customerSpecificationManager) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerSpecificationManager = customerSpecificationManager;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAll(Map<String, String> params) {
        Specification<Customer> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Customer> sp = customerSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(sp) : specification.and(sp);
        }
        return customerRepository.findAll(specification);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setDate(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        customerRepository.findById(id)
                .orElseThrow(() -> new DataProcessingException("Customer with id " + id + " not found in DB"));
        customer.setId(id);
        customer.setDate(new Date());
        return customerRepository.saveAndFlush(customer);
    }
}
