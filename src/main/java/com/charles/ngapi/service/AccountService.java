package com.charles.ngapi.service;

import com.charles.ngapi.config.security.SecurityUtils;
import com.charles.ngapi.entity.Account;
import com.charles.ngapi.entity.dto.CreateAccountDTO;
import com.charles.ngapi.entity.dto.UpdateAccountDTO;
import com.charles.ngapi.entity.dto.UpdateAccountPasswordDTO;
import com.charles.ngapi.entity.dto.UserDetailsDTO;
import com.charles.ngapi.enums.AccountRoleEnum;
import com.charles.ngapi.enums.AccountStatusEnum;
import com.charles.ngapi.exceptions.CustomException;
import com.charles.ngapi.repository.AccountRepository;
import com.charles.ngapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final AccountRepository repository;

    @Transactional
    public void create(CreateAccountDTO createAccountDTO) {
        Account account = modelMapper.map(createAccountDTO, Account.class);
        Optional<Account> accountOptional = repository.findByEmail(account.getEmail());
        if (accountOptional.isPresent()) {
            throw new CustomException(MessageUtils.ACCOUNT_EMAIL_EXISTS);
        }
        account.setStatus(AccountStatusEnum.ACTIVE);
        account.setRole(AccountRoleEnum.USER);
        account.setPassword(encoder.encode(account.getPassword()));
        repository.save(account);
    }

    public Account detail() {
        return repository.findById(getAuthAccount().getId()).orElseThrow(() -> new CustomException(MessageUtils.ACCOUNT_NOT_FOUND));
    }

    public Account get(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomException(MessageUtils.ACCOUNT_NOT_FOUND));
    }

    public Account getAccountByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new CustomException(MessageUtils.ACCOUNT_EMAIL_NOT_FOUND));
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getAuthAccount() {
        String email = SecurityUtils.getAuthEmail();
        return getAccountByEmail(email);
    }

    public List<GrantedAuthority> getRoles(String email) {
        Account account = getAccountByEmail(email);
        return Collections.singletonList(new SimpleGrantedAuthority(AccountRoleEnum.ADMIN.equals(account.getRole()) ? "ROLE_ADMIN" : "ROLE_USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = repository.findByEmailAndStatusNot(email, AccountStatusEnum.INACTIVE).orElseThrow(() -> new CustomException(MessageUtils.ACCOUNT_EMAIL_OR_STATUS_NOT_FOUND));
        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(AccountRoleEnum.ADMIN.equals(account.getRole()) ? "ROLE_ADMIN" : "ROLE_USER"));
        return new UserDetailsDTO(roles, account.getPassword(), account.getEmail());
    }

    @Transactional
    public void update(UpdateAccountDTO updateAccountDTO) {
        Account account = modelMapper.map(updateAccountDTO, Account.class);
        Account authAccount = getAuthAccount();
        authAccount.setName(account.getName());
    }

    @Transactional
    public void updatePassword(UpdateAccountPasswordDTO updateAccountPasswordDTO) {
        Account account = modelMapper.map(updateAccountPasswordDTO, Account.class);
        Account authAccount = getAuthAccount();
        if (!encoder.matches(updateAccountPasswordDTO.getCurrentPassword(), authAccount.getPassword())) {
            throw new CustomException(MessageUtils.ACCOUNT_PASSWORD_INCORRECT);
        }
        authAccount.setPassword(encoder.encode(account.getPassword()));
    }
}
