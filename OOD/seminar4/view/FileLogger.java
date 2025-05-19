package seminar4.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/*
 * A logger that writes log message to a file.
 */
public class FileLogger {
    private PrintWriter writer;

    /**
     * Creates a new instance that append message to the given file.
     * @param fileName the name of the file where the message should be logged to.
     */
    public FileLogger(String fileName) {
        try {
            writer = new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("File logger could not be initiated");
            e.printStackTrace();
        }
    }

    /**
     * Writes the log message to given file.
     * @param message The message to log.
     */
    public void log(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------\n");
        sb.append(message).append("\n");
        sb.append("Error occured at: ").append(new Timestamp(System.currentTimeMillis())).append("\n");
        sb.append("-------------------------------------------\n");
        writer.println(sb);
        writer.flush();
    }
}