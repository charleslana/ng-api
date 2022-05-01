package com.charles.ngapi.exceptions.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FieldErrorDTO {

    private String message;
    private Integer status;
    private Long timestamp;
}
