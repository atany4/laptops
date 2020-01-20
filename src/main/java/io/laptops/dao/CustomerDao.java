package io.laptops.dao;

import io.laptops.entity.Customer;

import java.util.List;

public interface CustomerDao {
    void create(Customer customer);
    Customer read(long id);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> getAll();
    void close();
}
