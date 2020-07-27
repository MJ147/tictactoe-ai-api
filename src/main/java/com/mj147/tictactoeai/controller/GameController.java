package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.controller.dto.BoardDto;
import com.mj147.tictactoeai.controller.dto.GameDto;
import com.mj147.tictactoeai.service.GameService;
import com.mj147.tictactoeai.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    MoveService moveService;

    @CrossOrigin(origins = "https://mj147.github.io")
    @PostMapping("/create")
    public ResponseEntity<GameDto> createGame() {
        GameDto gameDto = new GameDto(gameService.createGame());

        return ResponseEntity.ok(gameDto);
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable Long id) {
        GameDto gameDto = new GameDto(gameService.getGame(id));

        return ResponseEntity.ok(gameDto);
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @DeleteMapping("/{id}")
    public HttpStatus removeGame(@PathVariable Long id) {
        gameService.removeGame(id);

        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @GetMapping("/check/{boardId}")
    public ResponseEntity<Integer> checkIfWon(@PathVariable Long boardId) {
        Integer winStatus = gameService.checkIfWon(boardId);

        return ResponseEntity.ok(winStatus);
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @PutMapping("/reset/{boardId}")
    public ResponseEntity<BoardDto> resetBoard(@PathVariable Long boardId) {
        BoardDto boardDto = new BoardDto(gameService.resetBoard(boardId));
        return ResponseEntity.ok(boardDto);
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @PutMapping("/move")
    public ResponseEntity<BoardDto> makeMove(@RequestParam() Long squareId, @RequestParam() Boolean isAiPlayer) {
        BoardDto boardDto = new BoardDto(gameService.makeMove(squareId, isAiPlayer));

        return ResponseEntity.ok(boardDto);
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @PutMapping("/learn")
    public HttpStatus learnAi(@RequestParam Long gameId, @RequestParam Long numberOfGames) {
        Instant start = Instant.now();
        gameService.learnAi(gameId, numberOfGames);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @GetMapping("/save")
    public HttpStatus saveMovesToCsv(@RequestParam String fileName) {
        moveService.saveMovesToCsv(fileName);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "https://mj147.github.io")
    @PostMapping("/load")
    public ResponseEntity<Long> loadMovesFromCsv(@RequestParam String fileName) {
        Long numberOfMoves = moveService.loadMovesFromCsv(fileName);
        return ResponseEntity.ok(numberOfMoves);
    }

}
