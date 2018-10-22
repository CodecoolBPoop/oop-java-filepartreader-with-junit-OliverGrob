package com.codecool.firepartreaderwithjunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    private FilePartReader filePartReader;

    @BeforeEach
    void setUpEach() {
        this.filePartReader = new FilePartReader();
    }

    @Test
    void testIsFilePathAtConstructorCallNull() {
        assertNull(filePartReader.getFilePath());
    }

    @Test
    void testIsFromLineAtConstructorCallNull() {
        assertNull(filePartReader.getFromLine());
    }

    @Test
    void testIsToLineAtConstructorCallNull() {
        assertNull(filePartReader.getToLine());
    }

    @Test
    void testIsFilePathAtSetupIsNotNull() {
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getFilePath());
    }

    @Test
    void testIsFromLineAtSetupIsNotNull() {
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getFromLine());
    }

    @Test
    void testIsToLineAtSetupIsNotNull() {
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getToLine());
    }

    @Test
    void testIsToLineSmallerThanFromLineThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("resources/sample_text.txt", 2, 1);
        });
    }

    @Test
    void testIsFromLineSmallerThan1ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("resources/sample_text.txt", 0, 4);
        });
    }

    @Test
    void testRead() throws IOException {
        String text = "Where can I get some? " +
                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, " +
                "by injected humour, or randomised words which don't look even slightly believable. " +
                "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the " +
                "middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making " +
                "this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of " +
                "model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore " +
                "always free from repetition, injected humour, or non-characteristic words etc.";
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertEquals(text, filePartReader.read());
    }

    @Test
    void testReadThrowsExceptionOnIncorrectFilePath() {
        filePartReader.setup("resources/sampl_text.txt", 2, 4);
        assertThrows(NoSuchFileException.class, filePartReader::read);
    }

    @Test
    void testReadLines() {
        String text = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, " +
                "by injected humour, or randomised words which don't look even slightly believable. " +
                "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the";
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertEquals(text, filePartReader.readLines());
    }

    @Test
    void testReadLinesIfFromLineIsAndToLineAreBoth1() {
        String text = "Where can I get some?";
        filePartReader.setup("resources/sample_text.txt", 1, 1);
        assertEquals(text, filePartReader.readLines());
    }

}