package com.codecool.firepartreaderwithjunit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordAnalyzer {

    private final FilePartReader filePartReader;


    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }


    public ArrayList<String> wordsByABC() throws IOException {
        String text = this.filePartReader.readLines();
        return (ArrayList<String>) Stream.of(text.split("\\s+"))
                                    .map(word -> word.replaceAll("[.,!?]", ""))
                                    .sorted()
                                    .collect(Collectors.toList());
    }

    public ArrayList<String> wordsContainingSubString(String subString) throws IOException {
        String text = this.filePartReader.readLines();
        return (ArrayList<String>) Stream.of(text.split("\\s+"))
                                    .filter(word -> word.contains(subString))
                                    .collect(Collectors.toList());
    }

    public ArrayList<String> wordsArePalindrome() throws IOException {
        String text = this.filePartReader.readLines();
        return (ArrayList<String>) Stream.of(text.split("\\s+"))
                                    .filter(this::isPalindrome)
                                    .collect(Collectors.toList());
    }

    private boolean isPalindrome(String text) {
        StringBuilder reverse = new StringBuilder();
        String original = text.replaceAll("\\s+", "").toLowerCase();
        char[] characters = original.toCharArray();

        for (int i = characters.length - 1; i >= 0; i--) {
            reverse.append(characters[i]);
        }

        return (reverse.toString()).equals(original);
    }

    public FilePartReader getFilePartReader() {
        return filePartReader;
    }

}
