package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.CustomerReturn;

public interface CustomerReturnService {

    CustomerReturn addCustomerReturn(CustomerReturn customerReturn);

    List<CustomerReturn> getAllCustomerReturns();

    CustomerReturn getCustomerReturnById(Long id);
}
