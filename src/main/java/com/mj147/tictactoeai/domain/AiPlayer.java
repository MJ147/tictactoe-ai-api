package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;


@Data
@Entity
public class AiPlayer extends Player{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Move> moves;

    public Set<Move> getMoves() {
        if (moves == null) {
            moves = new HashSet<>();
        }
        return moves;
    }

    public void setMoves(Move move) {
        this.moves.add(move);
    }
}
