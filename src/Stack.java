import java.util.NoSuchElementException;

/**
 * Stack - A stack data structure for heap objects
 *
 * <pre>
 *
 * Assignment: #2
 * Course: ADEV-3001
 * Date Created: October 24, 2016
 *
 * Revision Log
 * Who          When    Reason
 * --------- ---------- ----------------------------------
 * Chris     16/11/07    Adding inner class Node
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public class Stack<E> {
    /**
     * Node - A node for a stack data structure
     *
     * <pre>
     *
     * Assignment: #2
     * Course: ADEV-3001
     * Date Created: October 24, 2016
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
    public class Node<E> {
        private E element;
        private Node<E> previous;

        /**
         * creates an instance of a node
         * @param element   The data of the node
         * @param previous  The previous node in the stack
         */
        public Node(E element, Node<E> previous) {
            this.element = element;
            this.previous = previous;
        }

        /**
         * gets the data from the node
         * @return The element
         */
        public E getElement() {
            return element;
        }

        /**
         * Gets the previous node
         * @return the previous node
         */
        public Node<E> getPrevious() {
            return previous;
        }
    }

    private Node<E> head;
    private int size;

    /**
     * Constructs a new Stack
     */
    public Stack() {
        size = 0;
    }

    /**
     * Adds a new element onto the stack
     * @param element   element to add
     */
    public void push(E element) {
        head = new Node<E>(element, head);
        size++;
    }

    /**
     * Get the element on the top of the stack, return's exception if empty
     * @return  the element on the top of the stack
     */
    public E top() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty");
        }
        return head.getElement();
    }

    /**
     * Take an element off of the stack, throws exception if empty
     * @return  the element removed from the stack
     */
    public E pop() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty");
        }
        Node<E> current = head;
        head = head.getPrevious();
        size--;
        return current.getElement();
    }

    /**
     * Gets the size of the stack
     * @return the size of the stack
     */
    public int size() {
        return size;
    }

    /**
     * returns whether or not the stack is empty
     * @return  true if the stack is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
