package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Integer size = 9;
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> squares;

    public List<Integer> getSquares() {
        if (squares == null) {
            squares = new ArrayList<>();
        }
        return squares;
    }
}
