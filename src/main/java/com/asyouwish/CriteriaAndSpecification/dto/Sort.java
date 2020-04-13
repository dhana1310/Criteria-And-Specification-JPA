package com.asyouwish.CriteriaAndSpecification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Sort {

    private String property;
    private Boolean isDescending = false;
}
