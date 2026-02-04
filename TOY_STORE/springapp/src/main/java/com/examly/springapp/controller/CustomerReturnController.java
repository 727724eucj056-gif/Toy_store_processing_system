package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.CustomerReturn;
import com.examly.springapp.repository.CustomerReturnRepo;

@RestController
@RequestMapping("/api/customer-returns")
public class CustomerReturnController {

    @Autowired
    private CustomerReturnRepo customerReturnRepo;

    @PostMapping
    public ResponseEntity<CustomerReturn> addCustomerReturn(@RequestBody CustomerReturn customerReturn) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerReturnRepo.save(customerReturn));
    }

    @GetMapping
    public ResponseEntity<List<CustomerReturn>> getAllCustomerReturns() {
        return ResponseEntity.ok(customerReturnRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerReturn> getCustomerReturn(@PathVariable Long id) {
        return ResponseEntity.ok(customerReturnRepo.findById(id).get());
    }
}
