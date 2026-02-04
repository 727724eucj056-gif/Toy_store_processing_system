package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.PurchaseOrderItem;
import com.examly.springapp.repository.PurchaseOrderItemRepo;

@RestController
@RequestMapping("/api/purchase-order-items")
public class PurchaseOrderItemController {

    @Autowired
    private PurchaseOrderItemRepo purchaseOrderItemRepo;

    @PostMapping
    public ResponseEntity<PurchaseOrderItem> addPurchaseOrderItem(@RequestBody PurchaseOrderItem item) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(purchaseOrderItemRepo.save(item));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<PurchaseOrderItem>> getItemsByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderItemRepo.findAll());
    }
}
