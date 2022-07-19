package ru.javarush.cryptoanalyser.likhter.toplevel;

import java.util.Scanner;

public class InputOptions {
    public static String[] options() {
        String[] encode = {"encode", "originalText.txt", "encodeText.txt", "5"};
        String[] decode = {"decode", "encodeText.txt", "decodeText.txt", "5"};
        String[] bruteforce = {"bruteforce", "encodeText.txt", "bruteText.txt"};
        String[] analyse = {"analyse", "encodeText.txt", "dictionary.txt", "analyse.txt"};
        System.out.println("choose an action: ");
        System.out.println("enter '1' to encode");
        System.out.println("enter '2' to decode");
        System.out.println("enter '3' to brute force");
        System.out.println("enter '4' to analyse");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.close();
        return switch (option) {
            case 1 -> encode;
            case 2 -> decode;
            case 3 -> bruteforce;
            case 4 -> analyse;
            default -> throw new IllegalStateException(" value: " + option);
        };
    }
}
