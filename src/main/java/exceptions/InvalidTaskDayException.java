package exceptions;

/**
 * Thrown when a task is given an invalid day string (e.g. not one of
 * the recognised weekdays).
 */
public class InvalidTaskDayException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTaskDayException() {
        super("Invalid day for task");
    }

    public InvalidTaskDayException(String message) {
        super(message);
    }

    public InvalidTaskDayException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTaskDayException(Throwable cause) {
        super(cause);
    }
}