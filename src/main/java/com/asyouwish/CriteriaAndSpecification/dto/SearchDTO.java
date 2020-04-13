package com.asyouwish.CriteriaAndSpecification.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDTO {

    private String searchInput;
    private Integer pageNumber;
    private Integer pageSize;
    private List<Filter> filters;
    private List<Sort> sortProperties;

}
