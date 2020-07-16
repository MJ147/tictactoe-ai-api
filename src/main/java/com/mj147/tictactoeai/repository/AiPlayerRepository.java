package com.mj147.tictactoeai.repository;

import com.mj147.tictactoeai.domain.AiPlayer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiPlayerRepository extends CrudRepository<AiPlayer, Long> {
}
