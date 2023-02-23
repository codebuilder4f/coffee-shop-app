package com.digital.customerservice.controller;


import com.digital.customerservice.CustomerServiceApplication;
import com.digital.customerservice.model.Customer;
import com.digital.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/" + CustomerServiceApplication.API_V + "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        customer.setScore(0);
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCustomerEmail(@PathVariable("id") long id) {
        try {
            String message = customerService.updateCustomerScore(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody JsonPatch patch) {
//        try {
//            Customer customer = customerService.findCustomer(id).orElseThrow(CustomerNotFoundException::new);
//            Customer customerPatched = applyPatchToCustomer(patch, customer);
//            customerService.updateCustomer(customerPatched);
//            return ResponseEntity.ok(customerPatched);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        } catch (CustomerNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
}
