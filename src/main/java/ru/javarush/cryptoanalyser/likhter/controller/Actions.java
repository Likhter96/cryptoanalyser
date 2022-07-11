package ru.javarush.cryptoanalyser.likhter.controller;

import ru.javarush.cryptoanalyser.likhter.commands.Action;
import ru.javarush.cryptoanalyser.likhter.commands.Decoder;
import ru.javarush.cryptoanalyser.likhter.commands.Encoder;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder());
    //TODO brute and snalyse
   // BRUTE_FORCE(action),
    //ANALYSE(action)

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;
    }
}
