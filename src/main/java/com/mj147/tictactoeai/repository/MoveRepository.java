package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.Move;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    @Query(value = "SELECT * FROM Move " +
            "WHERE board_symbol = (:symbol) " +
            "AND factor = (SELECT max(factor) FROM Move WHERE board_symbol = (:symbol)) " +
            "LIMIT 1", nativeQuery=true)
    Move findMax(@Param("symbol") String symbol);

    Boolean existsMoveByBoardSymbolAndSquareNumber(String symbol, Integer squareNumber);

    List<Move> findAllByUpdate(Boolean update);

}
