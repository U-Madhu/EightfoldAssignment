package com.eightfold.parser.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtReader {

    public static String read(String filePath) throws IOException {

        return Files.readString(Path.of(filePath));

    }
}