package com.codecool.firepartreaderwithjunit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FilePartReader {

    private String filePath;
    private Integer fromLine;
    private Integer toLine;


    public FilePartReader() {
        this.filePath = null;
        this.fromLine = null;
        this.toLine = null;
    }


    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException("fromLine and/or toLine has incorrect value(s)!");

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        return Files.lines(Paths.get(this.filePath))
                            .collect(Collectors.joining(" "));
    }

    public String readLines() throws IOException {
        return Files.lines(Paths.get(this.filePath))
                .skip(this.fromLine - 1)
                .limit(this.toLine - this.fromLine + 1)
                .collect(Collectors.joining(" "));
    }

}
