package com.mj147.tictactoeai.utils;

import com.mj147.tictactoeai.domain.Move;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private static final String ID = "Id";
    private static final String BOARD_SYMBOL = "Board_symbol";
    private static final String FACTOR = "Factor";
    private static final String MOVE_VALUE = "Move_value";
    private static final String UPDATE = "Update";

    public static void covertModelToCsv(List<Move> moves, String fileName) throws IOException {
        BufferedWriter out = Files.newBufferedWriter(Paths.get(fileName));
        CSVPrinter printer = CSVFormat.DEFAULT
                .withHeader(ID, BOARD_SYMBOL, FACTOR, MOVE_VALUE, UPDATE)
                .print(out);
        for (Move move : moves) {
            printer.printRecord(move.getId(), move.getBoardSymbol(), move.getFactor(), move.getMoveValue(), move.getUpdate());
        }
        printer.flush();
    }

    public static List<Move> covertCsvToModel(String fileName) throws IOException {
        List<Move> moves = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(ID, BOARD_SYMBOL, FACTOR, MOVE_VALUE, UPDATE)
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
            String boardSymbol = record.get(BOARD_SYMBOL);
            int factor = parseToInteger(record.get(FACTOR));
            int moveValue = parseToInteger(record.get(MOVE_VALUE));
            boolean update = Boolean.parseBoolean(record.get(UPDATE));

            Move move = new Move();
            move.setBoardSymbol(boardSymbol);
            move.setFactor(factor);
            move.setMoveValue(moveValue);
            move.setUpdate(update);
            moves.add(move);
        }
        return moves;
    }

    private static int parseToInteger(String record) {
        if ("".equals(record)) {
            record = "0";
        }

        return Integer.parseInt(record);
    }


}
