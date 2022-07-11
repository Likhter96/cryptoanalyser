package ru.javarush.cryptoanalyser.likhter.constants;

public class Alphabet {
    private static final String RUS = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String ENG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SIGNS = "!?@#$%^&*()_+-=[]{}|;:'/ ";
    public static final String ALPHABET = RUS + ENG + RUS.toLowerCase() + ENG.toLowerCase() + DIGITS + SIGNS;

    public static final char[] ALPHABET_ARRAY = ALPHABET.toCharArray();
}
