package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.Move;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    @Query(value = "SELECT * FROM Move " +
            "WHERE board_symbol IN (:board_symbols) " +
            "AND factor = (SELECT max(factor) FROM Move WHERE board_symbol IN (:board_symbols))",
            nativeQuery=true)
    List<Move> findMovesWithBiggestFactor(@Param("board_symbols") Set<String> boardSymbols);

    @Query(value = "SELECT * FROM Move " +
            "WHERE board_symbol IN (:board_symbols) " +
            "AND factor > (:factor_line)",
            nativeQuery=true)
    List<Move> findMovesByBoardSymbolsAndFactor(@Param("board_symbols") Set<String> boardSymbols, @Param("factor_line") Integer factor_line);

    @Query(value = "SELECT * FROM Move " +
            "WHERE board_symbol IN (:board_symbols)",
            nativeQuery=true)
    List<Move> findMovesByBoardSymbols(@Param("board_symbols") Set<String> boardSymbols);

    Boolean existsMoveByBoardSymbol(String symbol);

    List<Move> findAllByUpdate(Boolean update);

}
