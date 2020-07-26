package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
}
