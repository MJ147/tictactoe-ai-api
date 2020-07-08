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

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) {
        BoardDto boardDto = new BoardDto(boardService.getBoard(id));
        return  ResponseEntity.ok(boardDto);
    }

    @PutMapping("/update")
    public ResponseEntity<BoardDto> updateBoard(@RequestBody Board board) {
        BoardDto boardDto = new BoardDto(boardService.updateBoard(board));
        return ResponseEntity.ok(boardDto);
    }
}
