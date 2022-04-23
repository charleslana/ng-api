package com.charles.ngapi.controller;

import com.charles.ngapi.entity.dto.CreateAccountDTO;
import com.charles.ngapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping("/account")
    public ResponseEntity<Void> createAccount(@RequestBody @Valid CreateAccountDTO createAccountDTO) {
        log.info("Rest request to create account");
        accountService.create(modelMapper.map(createAccountDTO, CreateAccountDTO.class));
        return ResponseEntity.ok().build();
    }
}
