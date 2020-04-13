package com.asyouwish.CriteriaAndSpecification.service;

import com.asyouwish.CriteriaAndSpecification.dto.Filter;
import com.asyouwish.CriteriaAndSpecification.dto.Operator;
import com.asyouwish.CriteriaAndSpecification.dto.SearchDTO;
import com.asyouwish.CriteriaAndSpecification.dto.Sort;
import com.asyouwish.CriteriaAndSpecification.entity.Stock;
import com.asyouwish.CriteriaAndSpecification.entity.Store;
import com.asyouwish.CriteriaAndSpecification.repository.StockRepository;
import com.asyouwish.CriteriaAndSpecification.repository.StoreRepository;
import com.asyouwish.CriteriaAndSpecification.util.CriteriaAndSpecificationUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SpecificationService {

    private final StoreRepository storeRepository;
    private final StockRepository stockRepository;

    public List<Store> searchAllStoreUsingFilter(SearchDTO searchDTO) {

        return storeRepository.findAll((root, query, criteriaBuilder) ->
                getPredicateStore(searchDTO, root, query, criteriaBuilder)
        );
    }

    private Predicate getPredicateStore(SearchDTO searchDTO, Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getFilters())) {
            searchDTO.getFilters().forEach(filter ->
                    filterStore(searchDTO.getSearchInput(), filter, predicates,
                            criteriaBuilder, root)
            );
        }
        List<Order> orderList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getSortProperties())) {
            searchDTO.getSortProperties().forEach(sort ->
                    CriteriaAndSpecificationUtil.sortCustomerAndStore(sort, orderList, criteriaBuilder, root, Boolean.TRUE)
            );
        } else {
            CriteriaAndSpecificationUtil.applySort(new Sort(), orderList, criteriaBuilder, root, "storeName");
        }
        query.orderBy(orderList.toArray(new Order[0])); //this will automatically create the query using order by
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void filterStore(String searchInput, Filter filter, List<Predicate> predicates,
                             CriteriaBuilder criteriaBuilder, Root<Store> productRoot) {

        String property = filter.getProperty();
        switch (property) {
            case "storeName":
                if (filter.getOperator().equals(Operator.LIKE)) {
                    Predicate productNameLike = criteriaBuilder.like(productRoot.get("storeName"), "%" + searchInput + "%");
                    predicates.add(productNameLike);
                }
                break;
            case "email":
                if (filter.getOperator().equals(Operator.EQUAL)) {
                    Predicate modelEqual = criteriaBuilder.equal(productRoot.get("email"), filter.getValues().get(0));
                    predicates.add(modelEqual);
                }
                break;
            case "city":
            case "state":
                if (filter.getOperator().equals(Operator.EQUAL)) {
                    Predicate cityEqual = criteriaBuilder.equal(productRoot.get("city"), filter.getValues().get(0));
                    Predicate stateEqual = criteriaBuilder.equal(productRoot.get("state"), filter.getValues().get(1));

                    predicates.add(criteriaBuilder.and(cityEqual, stateEqual));
                }
                break;
            default:
                break;
        }
    }

    public List<Stock> searchAllStock(SearchDTO searchDTO) {
        return stockRepository.findAll((stockRoot, query, criteriaBuilder) ->
                getPredicateForStock(searchDTO, stockRoot, query, criteriaBuilder)
        );
    }

    private Predicate getPredicateForStock(SearchDTO searchDTO, Root<Stock> stockRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        String searchInput = searchDTO.getSearchInput();
        if (StringUtils.isNotBlank(searchInput)) {
            predicates.add(criteriaBuilder.like(stockRoot.get("store").get("storeName"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(stockRoot.get("product").get("productName"), "%" + searchInput + "%"));
            predicates.add(criteriaBuilder.like(stockRoot.get("quantity").as(String.class), "%" + searchInput + "%"));
        }
        Predicate[] orPredicates = predicates.toArray(new Predicate[0]);

        List<Order> orderList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(searchDTO.getSortProperties())) {
            searchDTO.getSortProperties().forEach(sort ->
                    CriteriaAndSpecificationUtil.sortStock(sort, orderList, criteriaBuilder, stockRoot)
            );
        } else {
            CriteriaAndSpecificationUtil.applySortForForeignKey(new Sort(), orderList, criteriaBuilder, stockRoot, "store", "storeName");
        }
        query.orderBy(orderList.toArray(new Order[0]));

        return criteriaBuilder.or(orPredicates);
    }
}
