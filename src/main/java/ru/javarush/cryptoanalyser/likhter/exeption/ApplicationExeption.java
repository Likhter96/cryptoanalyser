package ru.javarush.cryptoanalyser.likhter.exeption;

public class ApplicationExeption extends RuntimeException{
    public ApplicationExeption() {
    }
    public ApplicationExeption(String message) {
        super(message);
    }
    public ApplicationExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public ApplicationExeption(Throwable cause) {
        super(cause);
    }
}