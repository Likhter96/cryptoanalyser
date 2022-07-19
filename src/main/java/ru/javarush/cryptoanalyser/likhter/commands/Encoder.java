package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.constants.Alphabet;
import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.entity.ResultCode;
import ru.javarush.cryptoanalyser.likhter.exeption.ApplicationExeption;
import ru.javarush.cryptoanalyser.likhter.util.PathFinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        Path input = PathFinder.getRoot(parameters[0]);
        Path output = PathFinder.getRoot(parameters[1]);
        int key = Integer.parseInt(parameters[2]);
        try (BufferedReader reader = Files.newBufferedReader(input);
             BufferedWriter writer = Files.newBufferedWriter(output)
        ) {
            int value;
            int length = Alphabet.ALPHABET_ARRAY.length;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                character = Character.toLowerCase(character);
                if (Alphabet.ALPHABET_MAP.containsKey(character)) {
                    Integer index = Alphabet.ALPHABET_MAP.get(character);
                    index = (index + key) % length;
                    writer.write(Alphabet.ALPHABET_ARRAY[index]);
                } else if (character == '\n') {
                    writer.write(character);
                } else if (!Alphabet.ALPHABET_MAP.containsKey(character)) {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new ApplicationExeption("File not found ", e);
        }
        return new Result(ResultCode.OK, "encode all bytes from " + parameters[0] + " and write to " + parameters[1]);
    }
}