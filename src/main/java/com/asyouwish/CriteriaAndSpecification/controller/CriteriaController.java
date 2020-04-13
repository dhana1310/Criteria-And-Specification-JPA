package com.asyouwish.CriteriaAndSpecification.controller;

import com.asyouwish.CriteriaAndSpecification.dto.SearchDTO;
import com.asyouwish.CriteriaAndSpecification.entity.Customer;
import com.asyouwish.CriteriaAndSpecification.entity.Product;
import com.asyouwish.CriteriaAndSpecification.service.CriteriaService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criteria")
@AllArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @PostMapping("/searchCustomer")
    public ResponseEntity<List<Customer>> searchCustomerGrid(@RequestBody SearchDTO searchDTO) {

        List<Customer> customerList = criteriaService.searchCustomerGrid(searchDTO);
        return CollectionUtils.isNotEmpty(customerList) ?
                new ResponseEntity<>(customerList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/filterProducts")
    public ResponseEntity<List<Product>> fetchAllProductsUsingFilter(@RequestBody SearchDTO searchDTO) {

        List<Product> productList = criteriaService.fetchAllProductsUsingFilter(searchDTO);
        return CollectionUtils.isNotEmpty(productList) ?
                new ResponseEntity<>(productList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
