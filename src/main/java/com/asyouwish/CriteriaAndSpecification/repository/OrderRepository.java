package com.asyouwish.CriteriaAndSpecification.repository;

import com.asyouwish.CriteriaAndSpecification.entity.Customer;
import com.asyouwish.CriteriaAndSpecification.entity.Order;
import com.asyouwish.CriteriaAndSpecification.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<OrderItem, Order> {
}
