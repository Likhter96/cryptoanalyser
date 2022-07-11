package ru.javarush.cryptoanalyser.likhter;
import ru.javarush.cryptoanalyser.likhter.toplevel.CommandBuilder;
import ru.javarush.cryptoanalyser.likhter.controller.MainController;
import ru.javarush.cryptoanalyser.likhter.entity.Result;


public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        CommandBuilder commandBuilder = new CommandBuilder(mainController);
        Result result = commandBuilder.run(args);
        System.out.println(result);
    }
}
