/**
 * Logger - Global logging class
 *
 * <pre>
 *
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: September 26, 2016
 *
 * Revision Log
 * Who          When    Reason
 * --------- ---------- ----------------------------------
 *
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public class Logger {

    private static final String ERROR = "Error";
    private static final String INFO = "INFO";

    /**
     * logs content at the info level
     * @param message   message to log
     */
    public void logInfo(String message) {
        logHelper(Logger.INFO, message);
    }

    /**
     * formats and prints the log messages
     * @param level     the level of log message
     * @param message   message to log
     */
    private void logHelper(String level, String message) {
        System.out.println(level + "\t" + message);
    }

    /**
     * logs an error message
     * @param message   message of the error
     */
    public void logError(String message) {
        logHelper(Logger.ERROR, message);
    }
}
