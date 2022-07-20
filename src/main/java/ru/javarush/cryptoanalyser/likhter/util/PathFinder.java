package ru.javarush.cryptoanalyser.likhter.util;
import java.io.File;
import java.nio.file.Path;
public class PathFinder {
    private PathFinder() {
    }
    private static final String ROOT = System.getProperty("user.dir") + File.separator + "text" + File.separator;
    public static Path getRoot(String filename) {
        Path path = Path.of(filename);
        return path.isAbsolute() ? path : Path.of(ROOT + filename);
    }
}