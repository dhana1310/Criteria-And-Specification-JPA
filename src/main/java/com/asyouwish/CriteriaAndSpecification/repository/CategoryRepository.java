package com.asyouwish.CriteriaAndSpecification.repository;

import com.asyouwish.CriteriaAndSpecification.entity.Brand;
import com.asyouwish.CriteriaAndSpecification.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, String> {
}
