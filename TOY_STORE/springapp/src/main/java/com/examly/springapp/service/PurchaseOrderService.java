package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder);

    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder getPurchaseOrderById(Long id);

    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder);
}
