package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.controller.dto.BoardDto;
import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) {
        BoardDto boardDto = new BoardDto(boardService.getBoard(id));
        return  ResponseEntity.ok(boardDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/reset/{boardId}")
    public ResponseEntity<BoardDto> resetBoard(@PathVariable Long boardId) {
        BoardDto boardDto = new BoardDto(boardService.resetBoard(boardId));
        return ResponseEntity.ok(boardDto);
    }

}
