package com.manning.junitbook.ch04.rules;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5TempDirTester {

    /**
     * The advantage of the JUnit 5 extension approach is that we do not have to create the folder ourselves through a constructor;
     * the folder is created automatically when we annotate a field with **@TempDir**.
     */
    @TempDir
    Path tempDir;

    private static Path createdFile;

    @Test
    void testTemporaryFolder() throws IOException {
        assertTrue(Files.isDirectory(tempDir));
        createdFile = Files.createFile(tempDir.resolve("createdFile.txt"));
        assertTrue(createdFile.toFile().exists());
    }

    @AfterAll
    static void afterAll() {
        assertFalse(createdFile.toFile().exists());
    }
}
