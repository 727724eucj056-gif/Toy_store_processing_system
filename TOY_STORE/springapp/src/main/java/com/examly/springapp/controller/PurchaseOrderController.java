package com.examly.springapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.PurchaseOrder;
import com.examly.springapp.repository.PurchaseOrderRepo;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderRepo repo;

    // CREATE
    @PostMapping
    public ResponseEntity<PurchaseOrder> add(@RequestBody PurchaseOrder po) {
        po.setOrderDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(po));
    }

    // READ ALL
    @GetMapping
    public List<PurchaseOrder> getAll() {
        return repo.findAll();
    }

    // READ ONE (VIEW)
    @GetMapping("/{id}")
    public PurchaseOrder getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    // UPDATE STATUS
    @PutMapping("/{id}")
    public PurchaseOrder update(@PathVariable Long id,
                                @RequestBody PurchaseOrder po) {
        po.setPurchaseOrderId(id);
        return repo.save(po);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
