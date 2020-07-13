package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.controller.dto.SquareDto;
import com.mj147.tictactoeai.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/move/{squareId}")
    public ResponseEntity<SquareDto> makeMove(@PathVariable Long squareId) {
        SquareDto squareDto = new SquareDto(playerService.makeMove(squareId));

        return ResponseEntity.ok(squareDto);
    }

}
