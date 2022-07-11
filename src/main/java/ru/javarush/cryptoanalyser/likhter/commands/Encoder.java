package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.entity.ResultCode;
import ru.javarush.cryptoanalyser.likhter.exeption.ApplicationExeption;
import ru.javarush.cryptoanalyser.likhter.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Encoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        //TODO logic and Result message
        String txtFile = parameters[0];
        String encryptFile = parameters[0];
        Path path = Path.of(PathFinder.getRoot()+txtFile);
        try {
            List<String> strings = Files.readAllLines(path);
            for (String string : strings) {
                System.out.println(string);
            }
        } catch (IOException e) {
            throw new ApplicationExeption("File not found ", e);
        }
        return new Result(ResultCode.OK, "read all bytes " + path);
    }
}
