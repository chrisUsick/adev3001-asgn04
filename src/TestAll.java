import java.util.ArrayList;
import java.util.Arrays;

/**
 * TestAll - Main test program which runs all tests
 *
 * <pre>
 *
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: October 03, 2016
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
public class TestAll {
    /**
     * Main program which runs all tests
     * @param args          command line arguments
     * @throws Exception    If test execution fails an exception will be thrown
     */
    public static void main(String[] args) throws Exception {
        ArrayList<TestSuite> suites = new ArrayList<TestSuite>(Arrays.asList(
                new LinkedListTest()));
        for (TestSuite suite: suites) {
            suite.run();
        }
    }
}
