package seminar5.tests;

import org.junit.jupiter.api.*;
import seminar5.view.FileLogger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;


public class FileLoggerTest {
    private ByteArrayOutputStream  outContent;
    private PrintStream originalSysOut;

    @Before
    public void setUp(){
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams(){
        System.setOut(originalSysOut);
         outContent = null;
    }

    @Test
    public void testConstructorFailurePrintsToConsole() {
        new FileLogger("/invalid/file.txt"); 
        assertTrue(outContent.toString().contains("File logger could not be initiated"));
    }
}