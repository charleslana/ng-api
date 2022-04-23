package com.charles.ngapi.repository;

import com.charles.ngapi.entity.Account;
import com.charles.ngapi.enums.AccountStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByEmailAndStatusNot(String email, AccountStatusEnum status);
}
