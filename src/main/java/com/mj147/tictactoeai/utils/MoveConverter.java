package com.mj147.tictactoeai.utils;

import com.mj147.tictactoeai.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class MoveConverter {

    public static String convertMove(String boardSymbol) {
        int numberOfRotates = checkNumberOfRotates(boardSymbol);
        String newBoardSymbol = boardSymbol;
        if (numberOfRotates != 0) {
            newBoardSymbol = rotateBordSymbol(boardSymbol, numberOfRotates);
        }
        if (isMirrorRequired(newBoardSymbol)) {
            newBoardSymbol = mirrorBordSymbol(newBoardSymbol);
        }
        return newBoardSymbol;
    }

    public static String rotateBordSymbol(String boardValue, int numberOfRotates) {

        for (int i = 0; i < numberOfRotates; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(boardValue.charAt(2))
                    .append(boardValue.charAt(5))
                    .append(boardValue.charAt(8))
                    .append(boardValue.charAt(1))
                    .append(boardValue.charAt(4))
                    .append(boardValue.charAt(7))
                    .append(boardValue.charAt(0))
                    .append(boardValue.charAt(3))
                    .append(boardValue.charAt(6));
            boardValue = stringBuilder.toString();
        }
        return boardValue;
    }

    public static String mirrorBordSymbol(String boardValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(boardValue.charAt(0))
                .append(boardValue.charAt(3))
                .append(boardValue.charAt(6))
                .append(boardValue.charAt(1))
                .append(boardValue.charAt(4))
                .append(boardValue.charAt(7))
                .append(boardValue.charAt(2))
                .append(boardValue.charAt(5))
                .append(boardValue.charAt(8));
        boardValue = stringBuilder.toString();
        return boardValue;
    }

    public static Integer checkNumberOfRotates(String boardValue) {
        StringBuilder stringBuilder = new StringBuilder(boardValue);
        if ('1' == stringBuilder.charAt(0)) {
            return 0;
        }
        if ('1' == stringBuilder.charAt(2)) {
            return 1;
        }
        if ('1' == stringBuilder.charAt(8)) {
            return 2;
        }
        if ('1' == stringBuilder.charAt(6)) {
            return 3;
        }
        if ('2' == stringBuilder.charAt(0)) {
            return 0;
        }
        if ('2' == stringBuilder.charAt(2)) {
            return 1;
        }
        if ('2' == stringBuilder.charAt(8)) {
            return 2;
        }
        if ('2' == stringBuilder.charAt(6)) {
            return 3;
        }
        if ('1' == stringBuilder.charAt(1)) {
            return 0;
        }
        if ('1' == stringBuilder.charAt(5)) {
            return 1;
        }
        if ('1' == stringBuilder.charAt(7)) {
            return 2;
        }
        if ('1' == stringBuilder.charAt(3)) {
            return 3;
        }
        if ('2' == stringBuilder.charAt(1)) {
            return 0;
        }
        if ('2' == stringBuilder.charAt(5)) {
            return 1;
        }
        if ('2' == stringBuilder.charAt(7)) {
            return 2;
        }
        if ('2' == stringBuilder.charAt(3)) {
            return 3;
        }
        return 0;
    }

    public static Boolean isMirrorRequired(String boardValue) {
        StringBuilder sb = new StringBuilder(boardValue);
        if ('0' != sb.charAt(0)) {
            if (countZeros(String.valueOf(sb.charAt(1)) + sb.charAt(2) + sb.charAt(5)) > countZeros(String.valueOf(sb.charAt(3)) + sb.charAt(6) + sb.charAt(7))) {
                return true;
            }
            if (countZeros(String.valueOf(sb.charAt(1)) + sb.charAt(2) + sb.charAt(5)) == countZeros(String.valueOf(sb.charAt(3)) + sb.charAt(6) + sb.charAt(7)) &&
                    ((int)sb.charAt(1) + (int)sb.charAt(2) + (int)sb.charAt(5)) < ((int)sb.charAt(3) + (int)sb.charAt(6) + (int)sb.charAt(7))) {
                return true;
            }

        }

        return false;
    }

    public static int countZeros(String value){
        StringBuilder sb = new StringBuilder(value);
        int counter = 0;
        for (int i = 0; i < sb.length(); i++) {
            if ('0' == sb.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

    public static String convertBoardToString(Board board) {
        List<Square> squares = board.getSquares();
        String stringSquares = squares.stream()
                .map( s -> Integer.toString(s.getValue()))
                .collect(Collectors.joining());
        return stringSquares;
    }

    public static int convertResultToPoints(int checkIfWon, Integer moveValue) {
        if (checkIfWon == 3) return 1;
        if (checkIfWon == 1 || checkIfWon == 2) {
            if (checkIfWon == moveValue) {
                return 5;
            } else {
                return -5;
            }
        }
        return 0;
    }

    public static String addSquareToBoard(String boardSymbol, int squareNumber, int value) {
        StringBuilder sb = new StringBuilder(boardSymbol);
        sb.setCharAt(squareNumber, String.valueOf(value).charAt(0));
        return sb.toString();
    }
}
