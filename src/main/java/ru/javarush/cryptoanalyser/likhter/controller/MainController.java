package ru.javarush.cryptoanalyser.likhter.controller;
import ru.javarush.cryptoanalyser.likhter.commands.Action;
import ru.javarush.cryptoanalyser.likhter.entity.Result;

public class MainController {
    public Result execute(String command, String[] parameters) {
        Action action  = Actions.find(command);
        return action.execute(parameters);
    }
}