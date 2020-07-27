package com.mj147.tictactoeai.controller;

import com.mj147.tictactoeai.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    MoveService moveService;

    @CrossOrigin(origins = "https://tictactoe147.herokuapp.com/git")
    @DeleteMapping("/deleteAll")
    public HttpStatus createGame() {
        moveService.deleteAll();
        return HttpStatus.OK;
    }

}
