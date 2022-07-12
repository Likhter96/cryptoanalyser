package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.entity.Result;

public class Decoder implements Action{
    @Override
    public Result execute(String[] parameters) {

        String inputFile = parameters[0];
        String outputFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        return fileCopy(inputFile, outputFile, -1 * key);
    }
}
