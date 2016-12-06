import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * TestSuite - Class containing methods for creating writing tests
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
public class TestSuite {
    private Logger logger;
    private String name;

    /**
     * Default constructor
     * @param logger the logger to use for this class
     */
    public TestSuite(Logger logger) {
        this.logger = logger;
    }

    /**
     * Execute all methods with the <code>@Test</code> annotation
     * @throws Exception    If test execution fails,
     *                      the exception that was thrown by the test method will be thrown
     */
    public void run() throws Exception {

        ArrayList<Method> tests = this.getTestMethods();
        try {
            logger.logInfo("Executing tests for: " + getName());
            for (Method test : tests) {
                test.invoke(this);
            }
        } catch (Exception e) {
            // test invocation failed
            logger.logError("Test invocation failed");
            throw e;
        }
    }

    /**
     * Gets the method references for methods with the <code>@Test</code> annotation
     * @return  List of method references
     */
    public ArrayList<Method> getTestMethods() {
        Method[] methods = getClass().getDeclaredMethods();

        ArrayList<Method> testMethods = new ArrayList<>();
        for (Method method :
                methods) {
            if ( hasTestAnnotation(method)) {
                testMethods.add(method);
            }
        }

        return testMethods;
    }

    /**
     * Tests if a method reference is annotated with the <code>@Test</code> annotation
     * @param method    method to test
     * @return          true if the method has the annotation
     */
    private boolean hasTestAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        boolean res = false;
        for (Annotation a : annotations) {
            if (a instanceof Test) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Test helper method to assert if an expression is true
     * @param b                 expression to test
     * @throws AssertionError   thrown if <code>b</code> is false
     */
    protected void assertTrue(boolean b) throws AssertionError {
        String messageText = "assertion failure";
        assertTrue(b, messageText);
    }

    /**
     * Test helper method to assert if an expression is true
     * @param b                 expression to test
     * @param message           message to display if <code>b</code> is false
     * @throws AssertionError   thrown if <code>b</code> is false
     */
    protected void assertTrue(boolean b, String message) throws AssertionError {
        if (!b) {
            throw new AssertionError(message);
        }
    }

    /**
     * Asserts that the given Runnable throws the given exception
     * @param routine   code expected to throw an exception
     * @param exception exception class expected
     */
    protected void assertThrows(Runnable routine, Class<?> exception) {
        assertThrows(routine, exception, "");
    }

    /**
     * Asserts that the given Runnable throws the given exception
     * @param routine   code expected to throw an exception
     * @param exception exception class expected
     * @param message   message to display if assertion fails
     */
    protected void assertThrows(Runnable routine, Class<?> exception, String message) {
        try {
            routine.run();
            throw new AssertionError(
                    "Failed to throw exception. Original message: " + message);
        } catch (Exception e) {
            if (e.getClass() != exception) {
                throw new AssertionError(message, e);
            }
        }

    }

    /**
     * Executes the method given. throws exception if it fails
     * @param message   description of test
     * @param test      the test routine
     */
    public void runTest(String message, Runnable test) {
        try {
            test.run();
        } catch (Exception e) {
            // test failed
            throw new AssertionError("test failed " + message, e);

        }
    }


    /**
     * gets the name of the test suite
     * @return  the name of the test suite
     */
    public String getName() {
        return name;
    }

    /**
     * sets the of the test suite
     * @param name  new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
