package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.entity.ResultCode;
import ru.javarush.cryptoanalyser.likhter.exeption.ApplicationExeption;
import ru.javarush.cryptoanalyser.likhter.util.PathFinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.javarush.cryptoanalyser.likhter.constants.Alphabet.ALPHABET;

public class BruteForce implements Action {
    static int key;

    @Override
    public Result execute(String[] parameters) {
        Path input = PathFinder.getRoot(parameters[0]);
        Path output = PathFinder.getRoot(parameters[1]);
        StringBuilder builder = new StringBuilder();
        try (
                BufferedReader reader = Files.newBufferedReader(input);
                BufferedWriter writer = Files.newBufferedWriter(output)
        ) {
            for (int i = 1; i < ALPHABET.length() - 1; i++) {
                reader.mark(9999999);
                while (reader.ready()) {
                    int startByte = reader.read();
                    char startChar = (char) startByte;
                    if (ALPHABET.indexOf(startChar) != -1) {
                        int alphabetIndex = ALPHABET.indexOf(startChar);
                        int decryptedIndex = (alphabetIndex - i) % ALPHABET.length();
                        if (decryptedIndex < 0) {
                            decryptedIndex += ALPHABET.length();
                        }
                        char character = ALPHABET.charAt(decryptedIndex);
                        writer.write(character);
                        builder.append(character);
                    }
                }
                Pattern pattern = Pattern.compile(", [а-яА-Я]");
                Matcher matcher = pattern.matcher(builder);
                if (matcher.find()) {
                    key = i;
                    bruteText(input, output);
                    break;
                }
            }
        } catch (IOException e) {
            throw new ApplicationExeption(e.getMessage());
        }
        return new Result(ResultCode.OK, "BruteForce use key: " + key);
    }

    private void bruteText(Path input, Path output) {
        try (BufferedReader reader1 = Files.newBufferedReader(input);
             BufferedWriter writer1 = Files.newBufferedWriter(output)
        ) {
            while (reader1.ready()) {
                int indexOfOriginalChar = reader1.read();
                char originalChar = (char) indexOfOriginalChar;
                if (ALPHABET.indexOf(indexOfOriginalChar) != -1) {
                    int origCharIndexInAlphabet = ALPHABET.indexOf(originalChar);
                    int decryptedIndex = (origCharIndexInAlphabet - key) % ALPHABET.length();
                    if (decryptedIndex < 0) {
                        decryptedIndex += ALPHABET.length();
                    }
                    char newCharacter = ALPHABET.charAt(decryptedIndex);
                    writer1.write(newCharacter);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}