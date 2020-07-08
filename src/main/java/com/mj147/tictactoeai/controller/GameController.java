package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.controller.dto.GameDto;
import com.mj147.tictactoeai.domain.Game;
import com.mj147.tictactoeai.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<GameDto> createGame() {
        GameDto gameDto = new GameDto(gameService.createGame());

        return ResponseEntity.ok(gameDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable Long id) {
        GameDto gameDto = new GameDto(gameService.getGame(id));

        return ResponseEntity.ok(gameDto);
    }

}
