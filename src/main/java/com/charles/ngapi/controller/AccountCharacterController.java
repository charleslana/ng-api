package com.charles.ngapi.controller;

import com.charles.ngapi.entity.AccountCharacter;
import com.charles.ngapi.entity.dto.CreateAccountCharacterDTO;
import com.charles.ngapi.entity.dto.DetailAccountCharacterDTO;
import com.charles.ngapi.service.AccountCharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/account-character")
@RequiredArgsConstructor
public class AccountCharacterController {

    private final ModelMapper modelMapper;
    private final AccountCharacterService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Void> createCharacter(@RequestBody @Valid CreateAccountCharacterDTO createAccountCharacterDTO) {
        log.info("Rest request to create character account");
        service.createCharacter(modelMapper.map(createAccountCharacterDTO, CreateAccountCharacterDTO.class));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail")
    public ResponseEntity<DetailAccountCharacterDTO> getCharacterDetail() {
        log.info("Rest request to detail character account");
        AccountCharacter accountCharacter = service.getDetails();
        return ResponseEntity.ok(modelMapper.map(accountCharacter, DetailAccountCharacterDTO.class));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutCharacter() {
        log.info("Rest request to logout character account");
        service.logoutCharacter();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}")
    public ResponseEntity<Void> selectCharacter(@PathVariable("id") Long id) {
        log.info("Rest request to select character account");
        service.selectCharacter(id);
        return ResponseEntity.ok().build();
    }
}
