
/**
 * Node - A node to be used in a doubly linked list
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
public class Node<E> {
    private E element;
    private Node<E> previous;
    private Node<E> next;

    /**
     * constructs a node without setting any fields
     */
    public Node() {
    }

    /**
     * constructs a node by setting all fields
     * @param element   the data to be stored in the node
     * @param previous  the node that comes before this node
     * @param next      the node that comes after this node
     */
    public Node(E element, Node<E> previous, Node<E> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    /**
     * gets the element
     * @return The element
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element
     * @param element the data to put in the element fiel
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * gets the previous node
     * @return  the previous node
     */
    public Node<E> getPrevious() {
        return previous;
    }

    /**
     * sets the previous node
     * @param previous the new value
     */
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    /**
     * gets the next node
     * @return the next node
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * sets the next node
     * @param next the new next node
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
