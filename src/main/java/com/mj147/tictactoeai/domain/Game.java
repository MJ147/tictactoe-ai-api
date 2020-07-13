package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "game", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Board board;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Player> players;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }
}
