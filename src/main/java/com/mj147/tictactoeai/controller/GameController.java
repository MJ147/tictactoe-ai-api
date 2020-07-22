package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.controller.dto.BoardDto;
import com.mj147.tictactoeai.controller.dto.GameDto;
import com.mj147.tictactoeai.controller.dto.SquareDto;
import com.mj147.tictactoeai.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.Period;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public HttpStatus removeGame(@PathVariable Long id) {
        gameService.removeGame(id);

        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/check/{boardId}")
    public ResponseEntity<Integer> checkIfWon(@PathVariable Long boardId) {
        Integer winStatus = gameService.checkIfWon(boardId);

        return ResponseEntity.ok(winStatus);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/reset/{boardId}")
    public ResponseEntity<BoardDto> resetBoard(@PathVariable Long boardId) {
        BoardDto boardDto = new BoardDto(gameService.resetBoard(boardId));
        return ResponseEntity.ok(boardDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/move/{squareId}")
    public ResponseEntity<BoardDto> makeMove(@PathVariable Long squareId) {
        BoardDto boardDto = new BoardDto(gameService.makeMove(squareId));

        return ResponseEntity.ok(boardDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/learn")
    public HttpStatus learnAi(@RequestParam Long gameId, @RequestParam Long numberOfGames) {
        Instant start = Instant.now();
        gameService.learnAi(gameId, numberOfGames);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed);
        return HttpStatus.OK;
    }

}
