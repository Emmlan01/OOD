package seminar5.integration;


/**
 * Thrown when something goes wrong with the database. 
 */
public class DatabaseFailureException extends Exception {

    /**
     * Creates a new instance represents the condition
     * describe in the specfified message.
     * @param message A message that describes what went wrong.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
