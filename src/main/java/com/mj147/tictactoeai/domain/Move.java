package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardSymbol;
    private Integer moveValue;
    private Integer factor = 0;
    private Boolean update;

}
