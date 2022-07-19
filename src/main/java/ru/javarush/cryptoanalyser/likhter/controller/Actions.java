package ru.javarush.cryptoanalyser.likhter.controller;

import ru.javarush.cryptoanalyser.likhter.commands.*;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce()),
    ANALYSE(new Analyse());
    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;
    }
}