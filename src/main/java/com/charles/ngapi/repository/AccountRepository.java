package com.charles.ngapi.repository;

import com.charles.ngapi.entity.Account;
import com.charles.ngapi.enums.AccountStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a where a.email = ?1")
    Optional<Account> findByEmail(String email);

    @Query("select a from Account a where a.email = ?1 and a.status <> ?2")
    Optional<Account> findByEmailAndStatusNot(String email, AccountStatusEnum status);
}
