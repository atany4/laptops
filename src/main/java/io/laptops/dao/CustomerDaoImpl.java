package io.laptops.dao;

import io.laptops.entity.Customer;
import io.laptops.util.FactoryManager;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {
    private EntityManager entityManager = new FactoryManager().getEntityManager();

    public CustomerDaoImpl() {
    }

    @Override
    public void create(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(customer);
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer read(long id) {
        Customer customer = null;
        try {
            customer = entityManager.find(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            customer.setFullName(customer.getFullName());
            customer.setEmail(customer.getEmail());
            customer.setPhoneNumber(customer.getPhoneNumber());
            customer.setLogin(customer.getLogin());
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.remove(customer);
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT e FROM Customer e", Customer.class);
            list = (List<Customer>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Customer> findById(Long id) {
        List<Customer> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT e FROM Customer e WHERE e.id LIKE :id").setParameter("id", id);
            list = (List<Customer>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() {
        entityManager.close();
    }
}
