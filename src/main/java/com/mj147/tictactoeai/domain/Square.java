package com.mj147.tictactoeai.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Square {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value = 0;
    private int numberInBoard;
    @ManyToOne
    @JoinColumn(name = "board_id")
    @ToString.Exclude
    private Board board;
}
