package com.codecool.firepartreaderwithjunit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {

    @Test
    public void testIsFilePartReaderAtConstructorCallIsNotNull() {
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(new FilePartReader());
        assertNotNull(fileWordAnalyzer.getFILE_PART_READER());
    }

    @Test
    public void wordsByABC1() {
        String[] strings = {"addda", "fasd", "joli", "ocicacico", "qsub"};
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_test.txt", 1, 5);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    public void wordsByABC2() {
        String[] strings = {"joli", "ocicacico"};
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_test.txt", 4, 5);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    public void wordsByABC3() {
        String[] strings = {"addda", "fasd", "qsub"};
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_test.txt", 1, 3);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    public void wordsContainingSubString() {
        String[] strings = {"joli", "tvlteygsezroli", "bolikasderfa"};
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_test.txt", 3, 7);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsContainingSubString("oli").toString());
    }

    @Test
    public void wordsArePalindrome() {
        String[] strings = {"addda", "ocicacico"};
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_test.txt", 1, 5);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsArePalindrome().toString());
    }
}