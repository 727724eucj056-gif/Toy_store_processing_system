package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.PurchaseOrderItem;
import com.examly.springapp.repository.PurchaseOrderItemRepo;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

    @Autowired
    private PurchaseOrderItemRepo purchaseOrderItemRepo;

    @Override
    public PurchaseOrderItem addPurchaseOrderItem(PurchaseOrderItem item) {
        return purchaseOrderItemRepo.save(item);
    }

    @Override
    public List<PurchaseOrderItem> getItemsByOrderId(Long orderId) {
        return purchaseOrderItemRepo.findAll();
    }
}
