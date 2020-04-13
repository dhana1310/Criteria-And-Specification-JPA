package com.asyouwish.CriteriaAndSpecification.controller;

import com.asyouwish.CriteriaAndSpecification.dto.SearchDTO;
import com.asyouwish.CriteriaAndSpecification.entity.Stock;
import com.asyouwish.CriteriaAndSpecification.entity.Store;
import com.asyouwish.CriteriaAndSpecification.service.SpecificationService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specification")
@AllArgsConstructor
public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping("/searchStocks")
    public ResponseEntity<List<Stock>> searchStockGrid(@RequestBody SearchDTO searchDTO) {

        List<Stock> stockList = specificationService.searchAllStock(searchDTO);
        return CollectionUtils.isNotEmpty(stockList) ?
                new ResponseEntity<>(stockList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/filterStores")
    public ResponseEntity<List<Store>> fetchAllProductsUsingFilter(@RequestBody SearchDTO searchDTO) {

        List<Store> storeList = specificationService.searchAllStoreUsingFilter(searchDTO);
        return CollectionUtils.isNotEmpty(storeList) ?
                new ResponseEntity<>(storeList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
