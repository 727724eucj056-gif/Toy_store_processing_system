package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Supplier;

public interface SupplierService {

    Supplier addSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(Long id);

    Supplier updateSupplier(Long id, Supplier supplier);
}
