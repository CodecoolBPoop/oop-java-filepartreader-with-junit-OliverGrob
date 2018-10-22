package com.codecool.firepartreaderwithjunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileWordAnalyzerTest {

    private FilePartReader filePartReader;

    @BeforeEach
    void setUpEach() {
        filePartReader = mock(FilePartReader.class);
    }

    @Test
    void wordsByABC1() {
        String[] strings = {"addda", "fasd", "joli", "ocicacico", "qsub"};
        when(filePartReader.readLines()).thenReturn("qsub fasd addda joli ocicacico");
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    void wordsByABC2() {
        String[] strings = {"joli", "ocicacico"};
        when(filePartReader.readLines()).thenReturn("joli ocicacico");
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    void wordsByABC3() {
        String[] strings = {"addda", "fasd", "qsub"};
        when(filePartReader.readLines()).thenReturn("qsub fasd addda");
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsByABC().toString());
    }

    @Test
    void wordsContainingSubString() {
        String[] strings = {"joli", "tvlteygsezroli", "bolikasderfa"};
        when(filePartReader.readLines()).thenReturn("addda joli ocicacico tvlteygsezroli bolikasderfa");
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsContainingSubString("oli").toString());
    }

    @Test
    void wordsArePalindrome() {
        String[] strings = {"addda", "ocicacico"};
        when(filePartReader.readLines()).thenReturn("addda joli ocicacico tvlteygsezroli bolikasderfa");
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertEquals(Arrays.toString(strings), fileWordAnalyzer.wordsArePalindrome().toString());
    }
}