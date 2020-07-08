package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Square;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends CrudRepository<Square, Long> {

    Integer countAllByBoardIdAndAndValue(Long boardId, Integer value);
}
