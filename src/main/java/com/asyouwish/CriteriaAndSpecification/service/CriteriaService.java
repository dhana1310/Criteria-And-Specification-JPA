package com.asyouwish.CriteriaAndSpecification.service;

import com.asyouwish.CriteriaAndSpecification.dto.Filter;
import com.asyouwish.CriteriaAndSpecification.dto.Operator;
import com.asyouwish.CriteriaAndSpecification.dto.SearchDTO;
import com.asyouwish.CriteriaAndSpecification.dto.Sort;
import com.asyouwish.CriteriaAndSpecification.entity.Customer;
import com.asyouwish.CriteriaAndSpecification.entity.Product;
import com.asyouwish.CriteriaAndSpecification.util.CriteriaAndSpecificationUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CriteriaService {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Customer> searchCustomerGrid(SearchDTO searchDTO) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        String searchInput = searchDTO.getSearchInput();
        if(StringUtils.isNotBlank(searchInput)) {
//            criteriaBuilder.like(customerRoot.get("firstName"), "%" + searchInput + "%");
            predicates.add(criteriaBuilder.like(customerRoot.get("firstName"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("lastName"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("email"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("street"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("city"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("state"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("zipCode"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(customerRoot.get("phone"), "%" + searchInput + "%"));

        }
        Predicate[] orPredicates = predicates.toArray(new Predicate[0]);

        List<Order> orderList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getSortProperties())) {
            searchDTO.getSortProperties().forEach(sort ->
                    CriteriaAndSpecificationUtil.sortCustomerAndStore(sort, orderList, criteriaBuilder, customerRoot, Boolean.FALSE)
            );
        } else {
            CriteriaAndSpecificationUtil.applySort(new Sort(), orderList, criteriaBuilder, customerRoot, "firstName");
            CriteriaAndSpecificationUtil.applySort(new Sort(), orderList, criteriaBuilder, customerRoot, "lastName");
        }

        criteriaQuery.where(criteriaBuilder.or(orPredicates))  //where clause with all Predicates as OR
                .orderBy(orderList); //orderBy clause using request

        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public List<Product> fetchAllProductsUsingFilter(SearchDTO searchDTO) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getFilters())) {
            searchDTO.getFilters().forEach(filter ->
                    filterProduct(searchDTO.getSearchInput(), filter, predicates,
                            criteriaBuilder, productRoot)
            );
        }

        List<Order> orderList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getSortProperties())) {
            searchDTO.getSortProperties().forEach(sort ->
                    CriteriaAndSpecificationUtil.sortProduct(sort, orderList, criteriaBuilder, productRoot)
            );
        } else {
            CriteriaAndSpecificationUtil.applySort(new Sort(), orderList, criteriaBuilder, productRoot, "productName");
        }

        CriteriaQuery<Product> select = criteriaQuery.select(productRoot)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(orderList.toArray(new Order[0]));
        TypedQuery<Product> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();

    }

    private void filterProduct(String searchInput, Filter filter, List<Predicate> predicates,
                               CriteriaBuilder criteriaBuilder, Root<Product> productRoot) {

        String property = filter.getProperty();

        switch (property) {
            case "modelYear":
                if (filter.getOperator().equals(Operator.EQUAL)) {

                    Predicate modelEqual = criteriaBuilder.equal(productRoot.get("modelYear"), filter.getValues().get(0));
                    predicates.add(modelEqual);

                } else if (filter.getOperator().equals(Operator.NOT_EQUAL)) {

                    Predicate modelNotEqual = criteriaBuilder.notEqual(productRoot.get("modelYear"), filter.getValues().get(0));
                    predicates.add(modelNotEqual);

                } else if (filter.getOperator().equals(Operator.BETWEEN)) {
                    Predicate modelYearBetween = criteriaBuilder.between(productRoot.get("modelYear"),
                            Integer.valueOf(filter.getValues().get(0).toString()),
                            Integer.valueOf(filter.getValues().get(1).toString()));
                    predicates.add(modelYearBetween);
                }

                break;
            case "listPrice":
                if (filter.getOperator().equals(Operator.BETWEEN)) {
                    Predicate listPriceGreaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(productRoot.get("listPrice"), (Integer) filter.getValues().get(0));
                    predicates.add(listPriceGreaterThanOrEqualTo);

                    Predicate listPriceLessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(productRoot.get("listPrice"), (Integer) filter.getValues().get(1));
                    predicates.add(listPriceLessThanOrEqualTo);
                }
                break;
            case "brand":
                CriteriaAndSpecificationUtil.applySearchOnForeignKey(filter, predicates, productRoot, "brand", "brandDescription");
                break;
            case "category":
                CriteriaAndSpecificationUtil.applySearchOnForeignKey(filter, predicates, productRoot, "category", "categoryDescription");
                break;
            case "productName":
                if (filter.getOperator().equals(Operator.LIKE)) {
                    Predicate productNameLike = criteriaBuilder.like(productRoot.get("productName"), "%" + searchInput + "%");
                    predicates.add(productNameLike);
                }
                break;
            default:
                break;
        }

    }

}
