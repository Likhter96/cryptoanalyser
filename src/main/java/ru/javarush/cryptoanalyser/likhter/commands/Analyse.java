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
import java.util.*;
import java.util.stream.Collectors;

public class Analyse implements Action{
    @Override
    public Result execute(String[] parameters) {
        String encodeFile = parameters[0];
        String dictionaryFile = parameters[1];
        String analyzedFile = parameters[2];
        List<Character> dictChar = getSortedChars(dictionaryFile);
        List<Character> sourceChar = getSortedChars(encodeFile);
        Path source = PathFinder.getRoot(encodeFile);
        Path target = PathFinder.getRoot(analyzedFile);
        try (
                BufferedReader reader = Files.newBufferedReader(source);
                BufferedWriter writer = Files.newBufferedWriter(target)
        ) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                int index = sourceChar.indexOf(character);
                Character characterDecrypted = dictChar.get(index);
                writer.write(characterDecrypted != null ? characterDecrypted : character);
            }
        } catch (IOException e) {
            throw new ApplicationExeption(e.getMessage(), e);
        }
        return new Result(ResultCode.OK, analyzedFile);
    }
    private List<Character> getSortedChars(String encryptedFile) {
        Map<Character, Integer> map = createStartMap();
        Path path = PathFinder.getRoot(encryptedFile);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                character = Character.toLowerCase(character);
                if (map.containsKey(character)) {
                    Integer i = map.get(character);
                    map.put(character, ++i);
                }
            }
        } catch (IOException e) {
            throw new ApplicationExeption(e.getMessage(), e);
        }
        return map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).toList();
    }
    private Map<Character, Integer> createStartMap() {
        return Alphabet.ALPHABET_MAP.keySet().stream().collect(Collectors.toMap(character -> character, character -> 0, (a, b) -> b, LinkedHashMap::new));
    }
}