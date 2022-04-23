package com.charles.ngapi.controller;

import com.charles.ngapi.entity.Account;
import com.charles.ngapi.entity.dto.DetailAccountDTO;
import com.charles.ngapi.entity.dto.GetAccountDTO;
import com.charles.ngapi.entity.dto.UpdateAccountDTO;
import com.charles.ngapi.entity.dto.UpdateAccountPasswordDTO;
import com.charles.ngapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final ModelMapper modelMapper;
    private final AccountService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail")
    public ResponseEntity<DetailAccountDTO> detail() {
        log.info("Rest request to get detail authenticated account");
        Account account = service.detail();
        return ResponseEntity.ok(modelMapper.map(account, DetailAccountDTO.class));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    public ResponseEntity<GetAccountDTO> get(@PathVariable("id") Long id) {
        log.info("Rest request to get account id {}", id);
        Account account = service.get(id);
        return ResponseEntity.ok(modelMapper.map(account, GetAccountDTO.class));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<GetAccountDTO>> getAll() {
        log.info("Rest request to get all account");
        List<Account> accounts = service.getAll();
        return ResponseEntity.ok(accounts.stream().map(element -> modelMapper.map(element, GetAccountDTO.class)).collect(Collectors.toList()));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateAccountDTO accountDTO) {
        log.info("Rest request to update authenticated account");
        service.update(modelMapper.map(accountDTO, UpdateAccountDTO.class));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdateAccountPasswordDTO accountPasswordDTO) {
        log.info("Rest request to update authenticated account password");
        service.updatePassword(modelMapper.map(accountPasswordDTO, UpdateAccountPasswordDTO.class));
        return ResponseEntity.ok().build();
    }
}
