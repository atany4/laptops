package io.laptops.services;

import io.laptops.entity.Customer;

import java.util.List;

public interface CustomerService {
    void create(Customer customer);
    Customer read(long id);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> getAll();
    List<Customer> findById(Long id);
    void close();
}
