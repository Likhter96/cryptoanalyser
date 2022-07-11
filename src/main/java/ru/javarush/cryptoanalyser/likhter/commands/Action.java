package ru.javarush.cryptoanalyser.likhter.commands;

import ru.javarush.cryptoanalyser.likhter.entity.Result;

public interface Action {
    Result execute(String [] parameters);
}
