package com.codecool.firepartreaderwithjunit;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordAnalyzer {

    private final FilePartReader FILE_PART_READER;
    private final String TEXT;


    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.FILE_PART_READER = filePartReader;
        this.TEXT = filePartReader.readLines();
    }


    public ArrayList<String> wordsByABC() {
        return (ArrayList<String>) Stream.of(this.TEXT.split("\\s+"))
                                    .map(word -> word.replaceAll("[.,!?]", ""))
                                    .sorted()
                                    .collect(Collectors.toList());
    }

    public ArrayList<String> wordsContainingSubString(String subString) {
        return (ArrayList<String>) Stream.of(this.TEXT.split("\\s+"))
                                    .filter(word -> word.contains(subString))
                                    .collect(Collectors.toList());
    }

    public ArrayList<String> wordsArePalindrome() {
        return (ArrayList<String>) Stream.of(this.TEXT.split("\\s+"))
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

    public FilePartReader getFILE_PART_READER() {
        return FILE_PART_READER;
    }

}
