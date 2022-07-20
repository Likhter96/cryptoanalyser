package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.entity.ResultCode;
import ru.javarush.cryptoanalyser.likhter.exeption.ApplicationExeption;
import ru.javarush.cryptoanalyser.likhter.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Analyse implements Action {
    @Override
    public Result execute(String[] parameters) {
        String encodeFile = parameters[0];
        String dictionaryFile = parameters[1];
        String analyseWriteFile = parameters[2];
        Path input = PathFinder.getRoot(encodeFile);
        Path dictionary = PathFinder.getRoot(dictionaryFile);
        Path output = PathFinder.getRoot(analyseWriteFile);
        CharCounter read = new CharCounter();
        Map<Character, Integer> statisticSymbolsEncodedFile = read.countOfChar(input);
        Map<Character, Integer> statisticSymbolsDecodedFile = read.countOfChar(dictionary);
        String reader;
        try {
            reader = Files.readString(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        char[] encodedFileToChar = reader.toCharArray();
        List<Character> countForEnterSymbolsToEncFile = new ArrayList<>(statisticSymbolsEncodedFile.keySet());
        List<Character> countForEnterSymbolsToDecFile = new ArrayList<>(statisticSymbolsDecodedFile.keySet());

        for (int i = 0; i < countForEnterSymbolsToEncFile.size(); i++) {
            for (int j = 0; j < encodedFileToChar.length; j++) {
                int x = Character.compare(countForEnterSymbolsToEncFile.get(i), encodedFileToChar[j]);
                if (x == 0) {
                    encodedFileToChar[j] = countForEnterSymbolsToDecFile.get(i);
                }
            }
        }
        try {
            Files.writeString(output, String.valueOf(encodedFileToChar));
        } catch (IOException e) {
            throw new ApplicationExeption("IO error", e);
        }
        return new Result(ResultCode.OK, "analyse write to " + parameters[2]);
    }
}