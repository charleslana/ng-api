package com.charles.ngapi.config;

import com.charles.ngapi.entity.Account;
import com.charles.ngapi.entity.AccountCharacter;
import com.charles.ngapi.entity.Character;
import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import com.charles.ngapi.enums.AccountRoleEnum;
import com.charles.ngapi.enums.AccountStatusEnum;
import com.charles.ngapi.repository.AccountCharacterRepository;
import com.charles.ngapi.repository.AccountRepository;
import com.charles.ngapi.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class NgConfig {

    private final AccountCharacterRepository accountCharacterRepository;
    private final AccountRepository accountRepository;
    private final CharacterRepository characterRepository;
    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            accountCharacterRepository.deleteAll();
            characterRepository.deleteAll();
            accountRepository.deleteAll();

            Account account = new Account();
            account.setName("Test");
            account.setEmail("test@test.com");
            account.setPassword(encoder.encode("123456"));
            account.setRole(AccountRoleEnum.USER);
            account.setStatus(AccountStatusEnum.ACTIVE);

            Account account2 = new Account();
            account2.setName("Test 2");
            account2.setEmail("test2@test.com");
            account2.setPassword(encoder.encode("123456"));
            account2.setRole(AccountRoleEnum.USER);
            account2.setStatus(AccountStatusEnum.INACTIVE);

            accountRepository.saveAll(List.of(account, account2));

            Character character = new Character();
            character.setName("Naruto");
            character.setImage(1);

            Character character2 = new Character();
            character2.setName("Sasuke");
            character2.setImage(2);

            characterRepository.saveAll(List.of(character, character2));

            AccountCharacter accountCharacter = new AccountCharacter();
            accountCharacter.setAccount(account2);
            accountCharacter.setCharacter(character);
            accountCharacter.setLevel(1);
            accountCharacter.setName("Character1");
            accountCharacter.setStatus(AccountCharacterStatusEnum.INACTIVE);

            AccountCharacter accountCharacter2 = new AccountCharacter();
            accountCharacter2.setAccount(account2);
            accountCharacter2.setCharacter(character2);
            accountCharacter2.setLevel(1);
            accountCharacter2.setName("Character2");
            accountCharacter2.setStatus(AccountCharacterStatusEnum.ACTIVE);

            accountCharacterRepository.saveAll(List.of(accountCharacter, accountCharacter2));
        };
    }
}
