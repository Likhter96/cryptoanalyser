package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.constants.Alphabet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CharCounter {

    public Map<Character, Integer> countOfChar(Path path) {
        HashMap<Character, Integer> qwe = new HashMap<>();
        String read;
        Map<Character, Integer> sortedMap;
        try {
            read = Files.readString(path);
            char[] tt = read.toCharArray();
            for (int i = 0; i < Alphabet.ALPHABET_ARRAY.length; i++) {
                int count = 0;
                for (char c : tt) {
                    int x = Character.compare(Alphabet.ALPHABET_ARRAY[i], c);
                    if (x == 0) {
                        count++;
                    }
                    qwe.put(Alphabet.ALPHABET_ARRAY[i], count);
                }
            }
            sortedMap = qwe.entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getValue))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> {
                                throw new AssertionError();
                            },
                            LinkedHashMap::new
                    ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sortedMap;
    }
}