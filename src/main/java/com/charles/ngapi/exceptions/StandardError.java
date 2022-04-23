package com.charles.ngapi.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StandardError {

    private String message;
    private Integer status;
    private Long timestamp;
}
