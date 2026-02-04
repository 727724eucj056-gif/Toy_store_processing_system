package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.*;

public interface ProductService {
    Product addProduct(Product p);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product p);
}
