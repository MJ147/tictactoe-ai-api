package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Long removeById(Long id);
}
