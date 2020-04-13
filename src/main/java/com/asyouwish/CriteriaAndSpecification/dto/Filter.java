package com.asyouwish.CriteriaAndSpecification.dto;

import lombok.Data;

import java.util.List;

@Data
public class Filter {

    private String property;
    private Operator operator;
    private List<Object> values;
}
