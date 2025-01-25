package com.productmanagement.Product.Managemnt.controllers;


import com.productmanagement.Product.Managemnt.models.Customer;
import com.productmanagement.Product.Managemnt.service.CustomerService;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
public class CustomerController
{

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<?> addCustomer(@RequestBody @Valid Customer customer){
        if(customerService.addCustomer(customer)!=null){
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Customer not added", HttpStatus.CREATED);
    }
}
