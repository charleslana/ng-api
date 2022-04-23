package com.charles.ngapi.entity;

import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "account_character")
public class AccountCharacter {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "id")
    @Id
    @SequenceGenerator(name = "account_character_sequence", sequenceName = "account_character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_character_sequence")
    private Long id;
    @Column(name = "level", nullable = false)
    private Integer level;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCharacterStatusEnum status;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
