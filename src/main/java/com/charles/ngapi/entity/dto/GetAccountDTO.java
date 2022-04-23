package com.charles.ngapi.entity.dto;

import com.charles.ngapi.enums.AccountRoleEnum;
import com.charles.ngapi.enums.AccountStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class GetAccountDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private Long id;
    private String name;
    private AccountRoleEnum role;
    private AccountStatusEnum status;
}
