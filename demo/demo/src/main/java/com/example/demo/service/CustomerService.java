package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAll() { return customerRepo.findAll(); }
    public Customer getById(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
    public Customer save(Customer c) { return customerRepo.save(c); }
    public void delete(Long id) { customerRepo.deleteById(id); }
}
