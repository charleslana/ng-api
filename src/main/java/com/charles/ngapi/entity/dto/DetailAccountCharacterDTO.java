package com.charles.ngapi.entity.dto;

import com.charles.ngapi.entity.Character;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DetailAccountCharacterDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Character character;
    private Long id;
    private String name;
}
