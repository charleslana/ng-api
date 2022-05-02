package com.charles.ngapi.service;

import com.charles.ngapi.config.security.SecurityUtils;
import com.charles.ngapi.entity.AccountCharacter;
import com.charles.ngapi.entity.Character;
import com.charles.ngapi.entity.dto.CreateAccountCharacterDTO;
import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import com.charles.ngapi.exceptions.BusinessException;
import com.charles.ngapi.repository.AccountCharacterRepository;
import com.charles.ngapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCharacterService {

    private final AccountService accountService;
    private final CharacterService characterService;
    private final ModelMapper modelMapper;
    private final AccountCharacterRepository repository;

    public void createCharacter(CreateAccountCharacterDTO createAccountCharacterDTO) {
        AccountCharacter accountCharacter = modelMapper.map(createAccountCharacterDTO, AccountCharacter.class);
        existsAccountCharacterName(accountCharacter);
        existsAccountCharacter(createAccountCharacterDTO);
        Character character = characterService.existsCharacterId(createAccountCharacterDTO.getCharacterId());
        accountCharacter.setAccount(accountService.getAuthAccount());
        accountCharacter.setLevel(1);
        accountCharacter.setStatus(AccountCharacterStatusEnum.ACTIVE);
        accountCharacter.setCharacter(character);
        repository.save(accountCharacter);
    }

    public AccountCharacter getAuthCharacter() {
        existsCharacterId();
        Long id = SecurityUtils.getUserDetails().getCharacterId();
        return getAccountByCharacterId(id);
    }

    public AccountCharacter getDetails() {
        return getAuthCharacter();
    }

    public void logoutCharacter() {
        existsCharacterId();
        SecurityUtils.removeCharacterId();
    }

    public void selectCharacter(Long id) {
        AccountCharacter accountCharacter = getAccountByCharacterId(id);
        SecurityUtils.setCharacterId(accountCharacter.getId());
    }

    private void existsAccountCharacter(CreateAccountCharacterDTO createAccountCharacterDTO) {
        if (Boolean.TRUE.equals(repository.existsAccountCharacterByAccountAndCharacterId(accountService.getAuthAccount(), createAccountCharacterDTO.getCharacterId()))) {
            throw new BusinessException(MessageUtils.ACCOUNT_CHARACTER_EXISTS);
        }
    }

    private void existsAccountCharacterName(AccountCharacter accountCharacter) {
        Optional<AccountCharacter> accountCharacterOptional = repository.findByName(accountCharacter.getName());
        if (accountCharacterOptional.isPresent()) {
            throw new BusinessException(MessageUtils.ACCOUNT_CHARACTER_NAME_EXISTS);
        }
    }

    private void existsCharacterId() {
        if (Boolean.FALSE.equals(SecurityUtils.existsCharacterId())) {
            throw new BusinessException(MessageUtils.ACCOUNT_CHARACTER_NOT_FOUND);
        }
    }

    private AccountCharacter getAccountByCharacterId(Long id) {
        return repository.findByAccountIdAndIdAndStatus(accountService.getAuthAccount().getId(), id, AccountCharacterStatusEnum.ACTIVE).orElseThrow(() -> new BusinessException(MessageUtils.ACCOUNT_CHARACTER_NOT_FOUND));
    }
}
