package io.laptops.services;

import io.laptops.dao.CustomerDao;
import io.laptops.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;

    public CustomerServiceImpl() {
    }

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
