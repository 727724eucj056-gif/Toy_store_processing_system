package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.PurchaseOrderItem;

public interface PurchaseOrderItemService {

    PurchaseOrderItem addPurchaseOrderItem(PurchaseOrderItem item);

    List<PurchaseOrderItem> getItemsByOrderId(Long orderId);
}
