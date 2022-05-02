package com.charles.ngapi.repository;

import com.charles.ngapi.entity.Account;
import com.charles.ngapi.entity.AccountCharacter;
import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCharacterRepository extends JpaRepository<AccountCharacter, Long> {

    @Query("select (count(a) > 0) from AccountCharacter a where a.account = ?1 and a.character.id = ?2")
    Boolean existsAccountCharacterByAccountAndCharacterId(Account account, Long characterId);

    @Query("select a from AccountCharacter a where a.account.id = ?1 and a.id = ?2 and a.status = ?3")
    Optional<AccountCharacter> findByAccountIdAndIdAndStatus(Long accountId, Long id, AccountCharacterStatusEnum status);

    @Query("select a from AccountCharacter a where a.name = ?1")
    Optional<AccountCharacter> findByName(String name);
}
