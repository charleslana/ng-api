package com.charles.ngapi.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DetailAccountDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private Long id;
    private String name;
}
