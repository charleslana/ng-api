package com.charles.ngapi.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UpdateAccountDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;
}
