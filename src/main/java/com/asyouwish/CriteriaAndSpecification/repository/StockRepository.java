package com.asyouwish.CriteriaAndSpecification.repository;

import com.asyouwish.CriteriaAndSpecification.entity.Staff;
import com.asyouwish.CriteriaAndSpecification.entity.Stock;
import com.asyouwish.CriteriaAndSpecification.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StockRepository extends JpaRepository<Stock, Store>, JpaSpecificationExecutor<Stock> {
}
