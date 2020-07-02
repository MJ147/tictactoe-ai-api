package com.mj147.tictactoeai.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Integer size = 9;
    private List<Integer> fields;

    public List<Integer> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }
}
