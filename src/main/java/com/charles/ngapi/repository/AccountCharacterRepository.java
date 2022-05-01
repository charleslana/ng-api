package com.charles.ngapi.repository;

import com.charles.ngapi.entity.AccountCharacter;
import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCharacterRepository extends JpaRepository<AccountCharacter, Long> {

    Optional<AccountCharacter> findByAccountIdAndIdAndStatus(Long accountId, Long id, AccountCharacterStatusEnum status);

    Optional<AccountCharacter> findByName(String name);
}
