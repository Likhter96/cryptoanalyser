package ru.javarush.cryptoanalyser.likhter.app;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Application {
    public void run(String[] args) {
        String command = args[0];
        String [] parameters = Arrays.copyOfRange(args, 1, args.length);
    }
}
