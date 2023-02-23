package com.digital.customerservice.service;


import com.digital.customerservice.model.Customer;
import com.digital.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public String updateCustomerScore(Long id) {

        Customer customer = customerRepository.findById(id).get();// TODO : .get Wrong


//            Optional<Customer> customer = Optional.ofNullable(customers.get(mobileNumber));
//            customer.ifPresent(c -> {
//                c.setScore(c.getScore() + 1);
//                customers.put(mobileNumber, c);
//            });






        customer.setScore(customer.getScore()+1);
        customerRepository.save(customer);
        return "Customer score updated successfully";
    }
//https://github.com/Java-Techie-jt/patch-mapping-example/blob/main/src/main/java/com/javatechie/service/ProductService.java
//    public Product updateProductByFields(int id, Map<String, Object> fields) {
//        Optional<Product> existingProduct = repository.findById(id);
//
//        if (existingProduct.isPresent()) {
//            fields.forEach((key, value) -> {
//                Field field = ReflectionUtils.findField(Product.class, key);
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, existingProduct.get(), value);
//            });
//            return repository.save(existingProduct.get());
//        }
//        return null;
//    }
}
