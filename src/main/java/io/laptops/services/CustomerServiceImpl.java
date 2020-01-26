package io.laptops.services;

import io.laptops.dao.CustomerDao;
import io.laptops.dao.CustomerDaoImpl;
import io.laptops.entity.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public void create(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public Customer read(long id) {
        return customerDao.read(id);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public List<Customer> findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public void close() {
        customerDao.close();
    }
}
