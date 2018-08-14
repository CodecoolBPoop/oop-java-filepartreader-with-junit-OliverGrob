package com.codecool.firepartreaderwithjunit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    @Test
    public void testIsFilePathAtConstructorCallNull() {
        FilePartReader filePartReader = new FilePartReader();
        assertNull(filePartReader.getFilePath());
    }

    @Test
    public void testIsFromLineAtConstructorCallNull() {
        FilePartReader filePartReader = new FilePartReader();
        assertNull(filePartReader.getFromLine());
    }

    @Test
    public void testIsToLineAtConstructorCallNull() {
        FilePartReader filePartReader = new FilePartReader();
        assertNull(filePartReader.getToLine());
    }

    @Test
    public void testIsFilePathAtSetupIsNotNull() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getFilePath());
    }

    @Test
    public void testIsFromLineAtSetupIsNotNull() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getFromLine());
    }

    @Test
    public void testIsToLineAtSetupIsNotNull() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertNotNull(filePartReader.getToLine());
    }

    @Test
    public void testIsToLineSmallerThanFromLineThrowsException() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("resources/sample_text.txt", 2, 1);
        });
    }

    @Test
    public void testIsFromLineSmallerThan1ThrowsException() {
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> {
            filePartReader.setup("resources/sample_text.txt", 0, 4);
        });
    }

    @Test
    public void testRead() throws IOException {
        String text = "Where can I get some? " +
                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, " +
                "by injected humour, or randomised words which don't look even slightly believable. " +
                "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the " +
                "middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making " +
                "this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of " +
                "model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore " +
                "always free from repetition, injected humour, or non-characteristic words etc.";
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertEquals(text, filePartReader.read());
    }

    @Test
    public void testReadThrowsExceptionOnIncorrectFilePath() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sampl_text.txt", 2, 4);
        assertThrows(NoSuchFileException.class, (Executable) filePartReader::read);
    }

    @Test
    public void testReadLines() throws IOException {
        String text = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, " +
                "by injected humour, or randomised words which don't look even slightly believable. " +
                "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the";
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 2, 4);
        assertEquals(text, filePartReader.readLines());
    }

    @Test
    public void testReadLinesIfFromLineIsAndToLineAreBoth1() throws IOException {
        String text = "Where can I get some?";
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("resources/sample_text.txt", 1, 1);
        assertEquals(text, filePartReader.readLines());
    }

}