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

public class BruteForce implements Action {

    @Override
    public Result execute(String[] parameters) {
        Path input = PathFinder.getRoot(parameters[0]);
        Path output = PathFinder.getRoot(parameters[1]);
        int key = 0;
        int spaceCount = 0;
        char space = ' ';
        for (int keyCount = 0; key < Alphabet.ALPHABET_ARRAY.length; keyCount++) {
            int spaceCounter = countCharInFile(input, key, space);
            if (spaceCounter > spaceCount) {
                spaceCount = spaceCounter;
                key = keyCount;
            }
        }
        fileWriter(input, output, key);
        return new Result(ResultCode.OK, "BruteForce use key: " + key);
    }
    private int countCharInFile(Path input, int key, char space) {
        int spaceCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(input)) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                if (Alphabet.ALPHABET_MAP.containsKey(character)) {
                    int index = Alphabet.ALPHABET_MAP.get(character);
                    index = (index + key + Alphabet.ALPHABET_ARRAY.length) % Alphabet.ALPHABET_ARRAY.length;
                    if (Alphabet.ALPHABET_ARRAY[index] == space) {
                        spaceCount++;
                    }
                }
            }
        } catch (IOException e) {
            throw new ApplicationExeption("File not found ", e);
        }
        return spaceCount;
    }
    private void fileWriter(Path input, Path output, int key) {
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
                    index = (index - key) % length;
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
    }
}