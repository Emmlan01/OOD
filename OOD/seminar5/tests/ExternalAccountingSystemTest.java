package seminar5.tests;
import org.junit.jupiter.api.*;

import seminar5.integration.ExternalAccountingSystem;
import seminar5.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;


public class ExternalAccountingSystemTest {
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
    public void testUpdateSaleInformationPrintsConfirmation() {
        ExternalAccountingSystem accountingSystem = new ExternalAccountingSystem();
        Sale sale = new Sale();

        accountingSystem.updateSaleInformation(sale);

        String output = outContent.toString();
        assertTrue(output.contains("Sale information to external accounting system"),
            "The confirmation message should be printed to System.out.");
    }
}