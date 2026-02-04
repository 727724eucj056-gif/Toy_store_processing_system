package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
}
