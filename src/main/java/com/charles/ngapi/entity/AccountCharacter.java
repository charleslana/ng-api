package com.charles.ngapi.entity;

import com.charles.ngapi.enums.AccountCharacterStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "account_character")
public class AccountCharacter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_id")
    private Character character;
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
    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCharacterStatusEnum status;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
