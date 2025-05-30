package seminar5.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Before;


public class TotalRevenueViewTest {
    
    private ByteArrayOutputStream  outContent;
    private PrintStream originalSysOut;
    private TotalRevenueView totalRevenueView;

     @Before
    public void setUp(){
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        totalRevenueView = new TotalRevenueView();
    }

    @AfterEach
    public void cleanUpStreams(){
        System.setOut(originalSysOut);
         outContent = null;
    }

   private class TotalRevenueView extends seminar5.view.TotalRevenueView {
        public void callDoShowTotalRevenue(double total) throws IOException {
            doShowTotalRevenue(total);
        }
    }

    @Test
    public void testDoShowTotalRevenuePrintsToConsole() throws IOException {
        double total = 100;
        totalRevenueView.callDoShowTotalRevenue(total);
        String output = outContent.toString();
        assertTrue(output.contains("********** Total Revenue: " + total + "SEK"),
                "Expected output was not found in System.out");
    }

}
