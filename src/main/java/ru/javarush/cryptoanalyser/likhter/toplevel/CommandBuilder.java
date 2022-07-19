package ru.javarush.cryptoanalyser.likhter.toplevel;

import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.controller.MainController;
import java.util.Arrays;
import java.util.Scanner;

public class CommandBuilder {
    private final MainController mainController;
    public CommandBuilder(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run(String[] args) {
        String command = args[0];
        String [] parameters = Arrays.copyOfRange(args, 1, args.length);
        return mainController.execute(command, parameters);
    }
}
