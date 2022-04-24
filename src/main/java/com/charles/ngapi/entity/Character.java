package com.charles.ngapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "character")
public class Character {

    @Column(name = "id")
    @Id
    @SequenceGenerator(name = "character_sequence", sequenceName = "character_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_sequence")
    private Long id;
    @Column(name = "image", nullable = false)
    private Integer image;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
