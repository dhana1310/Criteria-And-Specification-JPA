package com.asyouwish.CriteriaAndSpecification.util;

import com.asyouwish.CriteriaAndSpecification.dto.Filter;
import com.asyouwish.CriteriaAndSpecification.dto.Operator;
import com.asyouwish.CriteriaAndSpecification.dto.Sort;
import com.asyouwish.CriteriaAndSpecification.entity.Product;
import com.asyouwish.CriteriaAndSpecification.entity.Stock;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaAndSpecificationUtil {

    public static <T> void applySortForForeignKey(Sort sortProperty, List<Order> orderList, CriteriaBuilder criteriaBuilder, Root<T> root,
                                                  String foreignKeyObject, String columnToBeSortedInFK) {
        if (!sortProperty.getIsDescending()) {
            Order orderAsc = criteriaBuilder.asc(root.get(foreignKeyObject).get(columnToBeSortedInFK));
            orderList.add(orderAsc);
        } else if (sortProperty.getIsDescending()) {
            Order orderDesc = criteriaBuilder.desc(root.get(foreignKeyObject).get(columnToBeSortedInFK));
            orderList.add(orderDesc);
        }
    }

    public static <T> void applySort(Sort sortProperty, List<Order> orderList, CriteriaBuilder criteriaBuilder, Root<T> root,
                                     String columnName) {
        if (!sortProperty.getIsDescending()) {
            Order orderAsc = criteriaBuilder.asc(root.get(columnName));
            orderList.add(orderAsc);
        } else if (sortProperty.getIsDescending()) {
            Order orderDesc = criteriaBuilder.desc(root.get(columnName));
            orderList.add(orderDesc);
        }
    }

    public static <T> void applySearchOnForeignKey(Filter filter, List<Predicate> predicates, Root<T> root,
                                                   String foreignKeyObject, String columnToBeSearchedInFK) {
        if (filter.getOperator().equals(Operator.IN)) {
            Predicate predicateIn = root.get(foreignKeyObject).get(columnToBeSearchedInFK).in(filter.getValues());
            predicates.add(predicateIn);
        } else if (filter.getOperator().equals(Operator.NOT_IN)) {
            Predicate predicateNotIn = root.get(foreignKeyObject).get(columnToBeSearchedInFK).in(filter.getValues()).not();
            predicates.add(predicateNotIn);
        }
    }

    public static void sortProduct(Sort sortProperty, List<Order> orderList, CriteriaBuilder criteriaBuilder, Root<Product> productRoot) {

        String property = sortProperty.getProperty();
        switch (property) {
            case "modelYear":
                applySort(sortProperty, orderList, criteriaBuilder, productRoot, "modelYear");
                break;
            case "listPrice":
                applySort(sortProperty, orderList, criteriaBuilder, productRoot, "listPrice");
                break;
            case "brand":
                applySortForForeignKey(sortProperty, orderList, criteriaBuilder, productRoot, "brand", "brandName");
                break;
            case "category":
                applySortForForeignKey(sortProperty, orderList, criteriaBuilder, productRoot, "category", "categoryName");
                break;
            default:
                applySort(sortProperty, orderList, criteriaBuilder, productRoot, "productName");

        }

    }

    public static void sortStock(Sort sortProperty, List<Order> orderList, CriteriaBuilder criteriaBuilder, Root<Stock> productRoot) {

        String property = sortProperty.getProperty();
        switch (property) {
            case "productName":
                applySortForForeignKey(sortProperty, orderList, criteriaBuilder, productRoot, "product", "productName");
                break;
            case "quantity":
                applySort(sortProperty, orderList, criteriaBuilder, productRoot, "quantity");
                break;
            default:
                applySortForForeignKey(sortProperty, orderList, criteriaBuilder, productRoot, "store", "storeName");
        }

    }

    public static <T> void sortCustomerAndStore(Sort sortProperty, List<Order> orderList, CriteriaBuilder criteriaBuilder, Root<T> root, Boolean isStore) {

        String property = sortProperty.getProperty();
        switch (property) {
            case "emailAndPhone":
                applySort(sortProperty, orderList, criteriaBuilder, root, "email");
                applySort(sortProperty, orderList, criteriaBuilder, root, "phone");
                break;
            case "address":
                applySort(sortProperty, orderList, criteriaBuilder, root, "city");
                applySort(sortProperty, orderList, criteriaBuilder, root, "state");
                applySort(sortProperty, orderList, criteriaBuilder, root, "street");
                applySort(sortProperty, orderList, criteriaBuilder, root, "zipCode");
                break;
            default:
                if (isStore) {
                    applySort(sortProperty, orderList, criteriaBuilder, root, "storeName");
                } else {
                    applySort(sortProperty, orderList, criteriaBuilder, root, "firstName");
                    applySort(sortProperty, orderList, criteriaBuilder, root, "lastName");
                }
        }
    }
}
