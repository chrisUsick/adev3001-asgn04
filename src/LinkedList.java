
import com.sun.tools.javac.util.GraphUtils;

import java.util.NoSuchElementException;

/**
 * LinkedList - This is a doubly linked list implementation.
 * It can be used as an ordered or an unordered list.
 * It is a 1 based implementation
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
public class LinkedList<E extends Comparable<E>> {
    private static final String NO_ELEMENT_EXISTS = "Element does not exist";
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructor - takes in no parameters
     */
    public LinkedList() {

    }

    /**
     * add an element to the beginning of the list
     * @param element   element to add to the list
     * @return          true if element gets added
     */
    public boolean add(E element) {
        linkHead(element);
        return true;
    }

    /**
     * Add an element after the given position
     * @param element       element to add to the list
     * @param position      the position to add after
     * @return              true if the element gets added
     */
    public boolean addAfter(E element, int position) {
        validatePosition(position);
        Node<E> current;
        if (position == size) {
            linkTail(element);
        } else {
            current = find(position);
            link(element, current, current.getNext());
        }

        return true;
    }

    /**
     * Add an element after the given data element
     * @param element   element to add
     * @param data      data to add after
     * @return          true if element is added
     */
    public boolean addAfter(E element, E data) {
        Node<E> current = find(data);
        if (current != null) {
            if (current == tail) {
                linkTail(element);
            } else {
                link(element, current, current.getNext());
            }
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return true;
    }

    /**
     * Adds an element before given position
     * @param element   element to add to the list
     * @param position  position to add before
     * @return          true if addition is sucessful
     */
    public boolean addBefore(E element, int position) {
        validatePosition(position);
        if (position == 1) {
            linkHead(element);
        } else {
            Node<E> current = find(position);
            link(element, current.getPrevious(), current);
        }
        return false;
    }

    /**
     * Adds an element before given data element
     * @param element   Element to insert to the list
     * @param data      data to insert before
     * @return          true if insertion is successful
     */
    public boolean addBefore(E element, E data) {
        Node<E> current = find(data);
        if (current != null) {
            if(current == head) {
                linkHead(element);
            } else {
                link(element, current.getPrevious(), current);
            }
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return true;
    }

    /**
     * adds an element to the first position in the list
     * @param data  data to add
     * @return      true if addition is successful
     */
    public boolean addFirst(E data) {
        linkHead(data);
        return true;
    }

    /**
     * add elements to the last position in the list
     * @param data  data to add
     * @return      true if addition is successful
     */
    public boolean addLast(E data) {
        linkTail(data);
        return true;
    }

    /**
     * Empties the contents of the list
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * check if the list has elements in runTest
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * get the first element at in the list
     * @return  The first element in the list
     */
    public E get() {
        return getFirst();
    }

    /**
     * Gets the element at the given position
     * @param position  position to retrieve
     * @return          the element at the given position
     */
    public E get(int position) {
        validatePosition(position);
        Node<E> node = find(position);
        return node.getElement();
    }

    /**
     * get the element that matches the provided data
     * @param data  data to search for
     * @return      the element that matches data
     */
    public E get(E data) {
        Node<E> current = find(data);
        if (current != null) {
            return current.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    /**
     * gets the first element in the list
     * @return the first element in the list
     */
    public E getFirst() {
        if (head != null) {
            return head.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    /**
     * gets the last element in the list
     * @return the last element in the list
     */
    public E getLast() {
        if (tail != null) {
            return tail.getElement();
        } else {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
    }

    /**
     * get the size of the list
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Inserts an element into the list in the correct order
     * If the list isn't ordered runTest will insert into the first valid location
     * @param data  the data to insert
     * @return      true if the insertion succeeded
     */
    public boolean insert(E data) {
        Node<E> previous = null, current;
        int compared;
        if (size == 0) {
            linkHead(data);
        } else {
            current = head;
            compared = current.getElement().compareTo(data);
            while(current != null && compared < 0) {
                previous = current;
                current = current.getNext();
                if (current != null) {
                    compared = current.getElement().compareTo(data);
                }
            }
            if (previous != null) {
                if (current != null) {
                    link(data, previous, current);
                } else {
                    linkTail(data);
                }
            } else {
                linkHead(data);
            }
        }
        return true;
    }

    /**
     * removes the head of the list
     * @return  the element that was removed
     */
    public E remove() {
        return unlinkHead();
    }

    /**
     * Removes the element at a given position
     * @param position  the position of the element to remove
     * @return          the element that was removed
     */
    public E remove(int position) {
        E result;
        validatePosition(position);
        if (position == 1) {
            result = unlinkHead();
        } else if (position == size) {
            result = unlinkTail();
        } else {
            Node<E> current = find(position);
            result = unlink(current);
        }
        return result;
    }

    /**
     * removes the element that matches the given data
     * @param data data to use to match the element
     * @return     element that was removed
     */
    public E remove(E data) {
        Node<E> current = find(data);
        E result;
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }

        if (current == head) {
            result = unlinkHead();
        } else if (current == tail) {
            result = unlinkTail();
        } else {
            result = unlink(current);
        }
        return result;
    }

    /**
     * removes the first element in the list
     * @return the element that was removed
     */
    public E removeFirst() {
        return remove();
    }

    /**
     * removes the last item of the list
     * @return the element that was removed
     */
    public E removeLast() {
        return unlinkTail();
    }

    /**
     * replaces the first element of the list
     * @param dataToReplace   the data to set the first element to
     * @return  the old element
     */
    public E set(E dataToReplace) {
        if (isEmpty()) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return setData(dataToReplace, head);
    }

    /**
     * replaces the element at the given index with new data
     * @param data      data to set the element to
     * @param position  position of the data to replace
     * @return          the old element
     */
    public E set(E data, int position) {
        validatePosition(position);
        E oldData;
        if (position == size) {
            oldData = setData(data, tail);
        } else {
            Node<E> current = find(position);
            oldData = setData(data, current);
        }
        return oldData;
    }

    /**
     * replaces the element that matches the given search data
     * @param dataToReplace   data used to replace
     * @param data            data used to find the element
     * @return                the old data in the replaced element
     */
    public E set(E dataToReplace, E data) {
        Node<E> current = find(data);
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        return setData(dataToReplace, current);
    }

    /**
     * replaces the first element of the list
     * @param data   the data to set the first element to
     * @return       the old element
     */
    public E setFirst(E data) {
        return set(data);
    }

    /**
     * replaces the last element of the list
     * @param data  the data to set the last element to
     * @return      the old element
     */
    public E setLast(E data) {
        return setData(data, tail);
    }

    /**
     * finds the node at the given position
     * @param position  position in the list
     * @return          the node
     */
    private Node<E> find(int position) {
        Node<E> current = head;
        int i = 1;
        while(i < position) {
            current = current.getNext();
            ++i;
        }
        return current;
    }

    /**
     * find the node with the given data
     * @param data  data to find
     * @return      node containing the data
     */
    private Node<E> find(E data) {
        Node<E> current = head;
        while(current != null) {
            if (current.getElement().compareTo(data) == 0) {
                break;
            }
            current = current.getNext();
        }

        return current;
    }

    /**
     * set the new element to the head of the list
     * @param element element to place at the head
     */
    private void linkHead(E element) {
        Node<E> toAdd = new Node<E>(element, null, head);
        if (head != null) {
            head.setPrevious(toAdd);
        }
        head = toAdd;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    /**
     * set the given element to the tail of the list
     * @param element element to place at tail
     */
    private void linkTail(E element) {
        Node<E> toAdd = new Node<>(element, tail, null);
        if (tail != null) {
            tail.setNext(toAdd);
        }
        tail = toAdd;
        if (size == 0) {
            head = tail;
        }
        size++;
    }

    /**
     * Link the given element to be in between the previous and current element
     * @param element   element to link
     * @param previous  the node that will be before the new node
     * @param current   the node that will be after the new node
     */
    private void link(E element, Node<E> previous, Node<E> current) {
        Node<E> toAdd = new Node<>(element, previous, current);
        previous.setNext(toAdd);
        current.setPrevious(toAdd);
        size++;
    }

    /**
     * sets the data of the given node
     * @param data
     * @param current
     * @return
     */
    private E setData(E data, Node<E> current) {
        if (current == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        E replacedData = current.getElement();
        current.setElement(data);
        return replacedData;
    }

    /**
     * removes the given node from the list
     * @param current the node to remove
     * @return        the data in the removed node
     */
    private E unlink(Node<E> current) {
        Node<E> previous = current.getPrevious(),
                next = current.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        --size;
        return current.getElement();
    }

    /**
     * removes the head node from the list
     * @return  the data in the head node which was removed
     */
    private E unlinkHead() {
        if (head == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        Node<E> current = head;
        head = current.getNext();
        --size;
        if (head == null) {
            tail = null;
        }

        return current.getElement();
    }

    /**
     * removes the tail node from the list
     * @return  the data in the removed tail node
     */
    private E unlinkTail() {
        if (tail == null) {
            throw new NoSuchElementException(NO_ELEMENT_EXISTS);
        }
        Node<E> current = tail;
        tail = current.getPrevious();
        if (tail != null) {
            tail.setNext(null);
        }
        --size;
        if (tail == null) {
            head = null;
        }

        return current.getElement();
    }

    /**
     * validates that the position is within the list
     * @param position position to validate
     */
    private void validatePosition(int position) {
        if ((position == size && size == 0) || position < 1 || position > size) {
            throw new NoSuchElementException(
                    String.format("Invalid position: %d", position));
        }
    }








}
