import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * StackTest - Test methods for the Stack
 *
 * <pre>
 *
 * Assignment: #2
 * Course: ADEV-3001
 * Date Created: November 14, 2016
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
public class StackTest {
    private Stack<String> stack;
    private String str1 = "str1";
    private String str2 = "str2";

    /**
     * Tests the push method
     * @throws Exception
     */
    @Test
    public void push() throws Exception {
        stack = new Stack<>();
        stack.push(str1);
        assertEquals("Failed to push to empty stack", stack.top(), str1);
        assertEquals(stack.size(), 1);
        stack.push(str2);
        assertEquals("Failed to push to stack with elements", stack.top(), str2);
        assertEquals(stack.size(), 2);
    }

    /**
     * Tests the top method
     * @throws Exception
     */
    @Test
    public void top() throws Exception {
        stack = new Stack<>();
        try {
            stack.top();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        stack.push(str1);
        assertEquals("Failed to show the top for empty stack", stack.top(), str1);
        stack.push(str2);
        assertEquals("Failed to show the top for stack with elements", stack.top(), str2);
    }

    /**
     * Tests the pop method
     * @throws Exception
     */
    @Test
    public void pop() throws Exception {
        stack = new Stack<>();
        try {
            stack.pop();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        stack.push(str1);
        stack.push(str2);
        assertEquals("Failed to pop the right element", stack.pop(), str2);
        assertEquals(stack.size(), 1);
    }

    /**
     * Tests the size method
     * @throws Exception
     */
    @Test
    public void size() throws Exception {
        stack = new Stack<>();
        assertEquals(stack.size(), 0);
        stack.push(str1);
        assertEquals(stack.size(), 1);
    }

    /**
     * Tests the isEmpty method
     * @throws Exception
     */
    @Test
    public void isEmpty() throws Exception {
        stack = new Stack<>();
        assertTrue(stack.isEmpty());
        stack.push(str1);
        assertTrue(!stack.isEmpty());
    }

}
