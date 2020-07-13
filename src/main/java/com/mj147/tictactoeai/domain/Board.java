package com.mj147.tictactoeai.domain;

import lombok.Data;
import lombok.ToString;

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
    @OneToOne()
    @MapsId
    @ToString.Exclude
    private Game game;
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Square> squares;

    public List<Square> getSquares() {
        if (squares == null) {
            squares = new ArrayList<>();
        }
        return squares;
    }
}
