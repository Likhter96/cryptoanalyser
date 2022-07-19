package ru.javarush.cryptoanalyser.likhter;
import ru.javarush.cryptoanalyser.likhter.toplevel.CommandBuilder;
import ru.javarush.cryptoanalyser.likhter.controller.MainController;
import ru.javarush.cryptoanalyser.likhter.entity.Result;
import ru.javarush.cryptoanalyser.likhter.toplevel.InputOptions;


public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        CommandBuilder commandBuilder = new CommandBuilder(mainController);
        Result result;
        if(args.length == 0){
            result = commandBuilder.run(InputOptions.options());
        }else {
            result = commandBuilder.run(args);
        }
        System.out.println(result);
    }
}
