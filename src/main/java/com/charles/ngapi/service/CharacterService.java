package com.charles.ngapi.service;

import com.charles.ngapi.entity.Character;
import com.charles.ngapi.exceptions.BusinessException;
import com.charles.ngapi.repository.CharacterRepository;
import com.charles.ngapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final ModelMapper modelMapper;
    private final CharacterRepository repository;

    public Character existsCharacterId(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(MessageUtils.CHARACTER_NOT_FOUND));
    }
}
