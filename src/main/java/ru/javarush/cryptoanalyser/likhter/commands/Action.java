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

public interface Action {
    Result execute(String[] parameters);

    default Result fileCopy(String inputFile, String outputFile, int key) {
        Path input = PathFinder.getRoot(inputFile);
        Path output = PathFinder.getRoot(outputFile);
        try (BufferedReader reader = Files.newBufferedReader(input);
             BufferedWriter writer = Files.newBufferedWriter(output)
        ) {
            int value;
            int length = Alphabet.ALPHABET_ARRAY.length;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                character = Character.toLowerCase(character);
                if (Alphabet.index.containsKey(character)) {
                    Integer index = Alphabet.index.get(character);
                    index = (index + key + Math.abs(key) * length) % length;
                    writer.write(Alphabet.ALPHABET_ARRAY[index]);
                } else if (character == '\n') {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new ApplicationExeption("File not found ", e);
        }
        return new Result(ResultCode.OK, outputFile + "write all bytes");
    }
}
