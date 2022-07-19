package ru.javarush.cryptoanalyser.likhter.constants;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private static final String RUS = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String ENG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SIGNS = "!?%^*()_+=[]{};:'/" ;
    private static final String ALPHABET = RUS + ENG + RUS.toLowerCase() + ENG.toLowerCase() + DIGITS + SIGNS;
    public static final char[] ALPHABET_ARRAY = ALPHABET.toCharArray();
    public static final  Map<Character, Integer> ALPHABET_MAP = new HashMap<>();

    static {
        for (int i = 0; i < ALPHABET_ARRAY.length; i++) {
            ALPHABET_MAP.put(ALPHABET_ARRAY[i], i);
        }
    }
}
