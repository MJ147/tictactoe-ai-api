package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.Square;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquareRepository extends CrudRepository<Square, Long> {

    Integer countAllByBoardIdAndValue(Long boardId, Integer value);

}
