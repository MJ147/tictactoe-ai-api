package com.mj147.tictactoeai.service;

import com.mj147.tictactoeai.domain.Board;
import com.mj147.tictactoeai.domain.Game;
import com.mj147.tictactoeai.domain.Player;
import com.mj147.tictactoeai.domain.Square;
import com.mj147.tictactoeai.exception.EntityDoesNotExistException;
import com.mj147.tictactoeai.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    GameRepository gameRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    PlayerService playerService;
    @Autowired
    SquareService squareService;

    @Override
    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);
        Player player1 = playerService.createPlayer();
        Player player2 = playerService.createPlayer();
        game.setPlayers(Arrays.asList(player1, player2));
        Board board = new Board();
        board.setGame(game);
        game.setBoard(board);
        boardService.createBoard(board);

        return game;
    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow( () -> new EntityDoesNotExistException("Game id: " + id + " not found"));
    }

    @Override
    public void removeGame(Long id) {
        getGame(id);
        gameRepository.removeById(id);
    }

    @Override
    public Integer checkIfWon(Long boardId) {
        List<Square> squares = boardService.getBoard(boardId).getSquares();
        int result = checkFromCenterSquare(squares);
        result = (result != 0) ? result : checkBoardOutline(squares);
        return (result == 0 && squareService.countAllByBoardIdAndAndValue(boardId,0) == 0) ? 3 : result;
    }

    //check if someone won starting from center square
    private Integer checkFromCenterSquare(List<Square> squares) {
        int centerSquareValue = squares.get(4).getValue();
        if (centerSquareValue != 0) {
            for (int i = 0; i < 4 ; i++) {
                if (squares.get(i).getValue() == centerSquareValue &&
                        squares.get(i).getValue() == convertToOppositeSquare(squares.get(i)).getValue()) {
                    return centerSquareValue;
                }
            }
        }
        return 0;
    }

    //check if someone won in board outline
    //renumbering of squares for easier finding
    private Integer checkBoardOutline(List<Square> squares) {
        List<Square> squaresInNewOrder = changeOrderOfSquares(squares);
        int counter = 0;
        for (int i = 1; i < squaresInNewOrder.size(); i++) {
            if (squaresInNewOrder.get(i).getValue() != 0 &&
                    squaresInNewOrder.get(i).getValue() == squaresInNewOrder.get(i - 1).getValue()) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 2) {
                return squaresInNewOrder.get(i).getValue();
            }
            if (i % 2 == 0) {
                counter = 0;
            }
        }
        return 0;
    }

    private List<Square> changeOrderOfSquares(List<Square> squares) {
        List<Square> squaresInNewOrder = new ArrayList<>();
        squaresInNewOrder.add(0, squares.get(0));
        squaresInNewOrder.add(1, squares.get(1));
        squaresInNewOrder.add(2, squares.get(2));
        squaresInNewOrder.add(3, squares.get(5));
        squaresInNewOrder.add(4, squares.get(8));
        squaresInNewOrder.add(5, squares.get(7));
        squaresInNewOrder.add(6, squares.get(6));
        squaresInNewOrder.add(7, squares.get(3));
        squaresInNewOrder.add(8, squares.get(0));

        return squaresInNewOrder;

    }

    private Square convertToOppositeSquare(Square square) {
        int squareNumber = 8 - square.getNumberInBoard();
        Square oppositeSquare = square.getBoard().getSquares().get(squareNumber);
        return oppositeSquare;
    }

}
