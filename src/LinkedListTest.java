import java.util.NoSuchElementException;

/**
 * LinkedListTest - Test class for LinkedList. Includes a main method so it can be run standalone or
 * called programmatically
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
 * Chris     04/12/2016 Removed employee tests
 * </pre>
 *
 * @author Chris Usick
 * @version 1.0
 *
 */
public class LinkedListTest extends TestSuite {
    private final String string1 = "str1";
    private final String string2 = "str2";
    private final String string3 = "str3";

    /**
     * defualt constructor. sets the name and logger
     */
    public LinkedListTest() {
        super(new Logger());
        setName("Linked Lists");
    }

    /**
     * Execute this test
     * @param args          command line arguments
     * @throws Exception    If test execution throws an exception the application exists
     */
    public static void main(String[] args) throws Exception{
        LinkedListTest testSuite = new LinkedListTest();
        testSuite.run();
    }

    /**
     * Asserts that the given list is empty
     * @param list  list to check
     */
    public void assertListEmpty(LinkedList<?> list) {
        assertThrows(() -> list.getFirst(), NoSuchElementException.class, "Head isn't null");
        assertThrows(() -> list.getLast(), NoSuchElementException.class, "Tail isn't null");
        assertTrue(list.getSize() == 0, "List size is not 0");
    }

    /**
     * test list is constructed correctly
     */
    @Test
    public void testConstructor() {
        runTest("inits correctly", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.getSize() == 0);
        });
    }

    /**
     * test the add method
     */
    @Test
    public void add() {
        runTest("adds an element to the head of empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            String testString = "Foo";
            list.add(testString);
            assertTrue(list.getFirst() == testString, "head isn't Set");
            assertTrue(list.getLast() == testString, "tail isn't set");
            assertTrue(list.getSize() == 1, "size isn't correct");
        });

        runTest("adds an element to the head of list with data", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.add(string2);

            assertTrue(list.getFirst() == string2);
            assertTrue(list.getLast() == string1);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the addAfter with Position method
     */
    @Test
    public void addAfterPosition() {
        runTest("doesn't add a element if position greater than size", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", 2);
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("doesn't add an element if position is less than 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", -1);
                assertTrue(false, "Added an element for an invalid position.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("doesn't add an element if list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", 0);
                // if we get here, then the test failed
                assertTrue(false, "addAfter added an element to an empty list");
            } catch (NoSuchElementException e) {
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("adds after to index 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            String str0 = "str0";
            String string1 = "str1";
            list.add(str0);
            boolean success = list.addAfter(string1, 1);
            assertTrue(success);
            assertTrue(list.getFirst() == str0);
            assertTrue(list.getLast() == string1);
            assertTrue(list.getSize() == 2);
        });

        runTest("adds after to the last element in the list", () -> {
            LinkedList<String> list = new LinkedList<>();
            String string0 = "str0";
            list.add("str1");
            list.add(string0);
            list.addAfter(string2, 2);
            assertTrue(list.getFirst() == string0);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 3);
        });

        runTest("adds after the first element in a multi element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string1);
            list.addAfter(string2, 1);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.get(2) == string2);
            assertTrue(list.getSize() == 3);
        });
    }

    /**
     * test the addAfter with Data method
     */
    @Test
    public void addAfterData() {
        runTest("doesn't add an element if the data doesn't exist", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addAfter("str1", "str0");
                assertTrue(false, "added an element after an element that doesn't exist.");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("updates the tail for 1 element lists", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            boolean success = list.addAfter(string2, string1);
            assertTrue(success);
            assertTrue(list.getLast() == string2, "last item wasn't added");
            assertTrue(list.getSize() == 2, "size is incorrect");
        });

        runTest("adds to the middle of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string1);
            list.addAfter(string2, string1);
            assertTrue(list.get(2) == string2);
            assertTrue(list.getSize() == 3);
        });
    }

    /**
     * test the addBefore with Position method
     */
    @Test
    public void addBeforePosition() {
        runTest("doesn't add data if position is less than 0", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(string1, -1);
                assertTrue(false, "added an item before a negative index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("doesn't add data to an index that is too large", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(string1, 5);
                assertTrue(false, "added an item to an invalid index");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("doesn't add an element at index 0 if list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore(string1, 0);
                assertTrue(false, "added an item at 0 to an empty list");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("adds an element before head of index 1 list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.addBefore(string1, 1);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });

        runTest("adds an element in the middle of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string1);
            list.addBefore(string2, 2);
            assertTrue(list.get(2) == string2);
            assertTrue(list.getSize() == 3);
        });
    }

    /**
     * test the addBefore with Data method
     */
    @Test
    public void addBeforeData() {
        runTest("doesn't add data if data doesn't exist", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.addBefore("str1", "str0");
                assertTrue(false, "added element before a non-existent element");
            } catch (NoSuchElementException e) {
                assertTrue(true);
                assertTrue(list.getSize() == 0);
            }
        });

        runTest("adds before the head", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            boolean success = list.addBefore(string1, string2);
            assertTrue(success);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getSize() == 2);
        });

        runTest("adds to the middle of the list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string1);
            list.addBefore(string2, string3);
            assertTrue(list.get(2) == string2);
            assertTrue(list.getSize() == 3);
        });
    }

    /**
     * test the addFirst method
     */
    @Test
    public void addFirst() {
        runTest("adds to an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            boolean success = list.addFirst(string1);
            assertTrue(success);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string1);
            assertTrue(list.getSize() == 1);
        });

        runTest("adds to a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.addFirst(string1);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the addLast method
     */
    @Test
    public void addLast() {
        runTest("adds to an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.addLast(string1);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string1);
            assertTrue(list.getSize() == 1);
        });

        runTest("adds to list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.addLast(string2);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the clear method
     */
    @Test
    public void clear() {
        runTest("clears an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.clear();
            assertListEmpty(list);
        });

        runTest("clears a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.add(string2);
            list.clear();
            assertListEmpty(list);
        });
    }

    /**
     * test the isEmpty method
     */
    @Test
    public void isEmpty() {
        runTest("tests an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.isEmpty());
        });

        runTest("tests a list with elements", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(!list.isEmpty());
        });
    }

    /**
     * test the get with No Argument method
     */
    @Test
    public void getNoArgument() {
        runTest("throws an exception when list is empty", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(), NoSuchElementException.class);
        });

        runTest("returns the head on empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(list.get() == string1);
        });

        runTest("returns the head on containing data", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.add(string1);
            assertTrue(list.get() == string1);
        });
    }

    /**
     * test the get with Data method
     */
    @Test
    public void getData() {
        runTest("doesn't get an element that doesn't exist in list", () -> {
            LinkedList<String> list = new LinkedList<>();
            try {
                list.get(string1);
                assertTrue(false, "didn't throw an exception");
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }
        });

        runTest("gets an element from 1 element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(list.get("str1") == string1);
        });

        runTest("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertTrue(list.get("str2") == string2);
        });
    }

    /**
     * test the get with Position method
     */
    @Test
    public void getPosition() {
        runTest("throws exception negative position", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(-1), NoSuchElementException.class);
        });

        runTest("throws exception for position greater than size", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.get(2), NoSuchElementException.class);
        });

        runTest("gets an element from a 1 element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(list.get(1) == string1);
        });

        runTest("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertTrue(list.get(2) == string2);
        });
    }

    /**
     * test the getFirst method
     */
    @Test
    public void getFirst() {
        runTest("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.getFirst(), NoSuchElementException.class);
        });

        runTest("gets the head for a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(list.getFirst() == string1);
        });

        runTest("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertTrue(list.getFirst() == string1);
        });
    }

    /**
     * test the getLast method
     */
    @Test
    public void getLast() {
        runTest("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.getLast(), NoSuchElementException.class);
        });

        runTest("gets the tail for a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertTrue(list.getLast() == string1);
        });

        runTest("gets an element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertTrue(list.getLast() == string3);
        });
    }

    /**
     * test the getSize method
     */
    @Test
    public void getSize() {
        runTest("gets size for an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertTrue(list.getSize() == 0);
        });
        runTest("gets size for an multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertTrue(list.getSize() == 3);
        });
    }

    /**
     * test the insert method
     */
    @Test
    public void insert() {
        runTest("inserts into an empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(string1);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getSize() == 1);
        });

        runTest("inserts into a properly sorted list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(string1);
            list.insert(string3);
            list.insert(string2);
            assertTrue(list.get(2) == string2);
            assertTrue(list.get(3) == string3);
            assertTrue(list.getSize() == 3);
        });

        runTest("inserts into a unsorted list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.add(string2);
            list.insert(string3);
            assertTrue(list.get(3) == string3);
            assertTrue(list.get(1) == string2);
            assertTrue(list.get(2) == string1);
        });

        runTest("inserts to the end of a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.insert(string2);
            list.insert(string1);
            list.insert(string3);
            assertTrue(list.getSize() == 3);
            assertTrue(list.get(2) == string2);
            assertTrue(list.get(3) == string3);
        });

    }

    /**
     * test the removeHead method
     */
    @Test
    public void removeHead() {
        runTest("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(), NoSuchElementException.class);
        });

        runTest("removes element on single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.remove();
            assertTrue(list.getSize() == 0);
            assertThrows(() -> list.get(), NoSuchElementException.class);
        });

        runTest("removes element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.add(string1);
            list.remove();
            assertTrue(list.getFirst() == string2);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 1);
        });
    }

    /**
     * test the remove with Position method
     */
    @Test
    public void removePosition() {
        runTest("throws exception for negative index", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(-1), NoSuchElementException.class);
        });

        runTest("throws exception for index not in list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            assertThrows(() -> list.remove(2), NoSuchElementException.class);
        });

        runTest("removes element in single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.remove(1);
            assertListEmpty(list);
        });

        runTest("removes element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.remove(2);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string3);
            assertTrue(list.getSize() == 2);
        });

        runTest("removes the last element in a list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.remove(3);
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the remove with Data method
     */
    @Test
    public void removeData() {
        runTest("throws exception for missing data", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.remove(string1), NoSuchElementException.class);
        });

        runTest("removes a element from a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.remove("str1");
            assertListEmpty(list);
        });

        runTest("removes an element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.remove("str2");
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string3);
            assertTrue(list.getSize() == 2);
        });

        runTest("removes last element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.remove("str3");
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the removeFirst method
     */
    @Test
    public void removeFirst() {
        runTest("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.removeFirst(), NoSuchElementException.class);
            assertTrue(list.getSize() == 0);
        });

        runTest("removes an element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.removeFirst();
            assertListEmpty(list);
        });

        runTest("removes first element from multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.add(string1);
            list.removeFirst();
            assertTrue(list.getFirst() == string2);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 1);
        });
    }

    /**
     * test the removeLast method
     */
    @Test
    public void removeLast() {
        runTest("throws an exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.removeLast(), NoSuchElementException.class);
        });

        runTest("removes the last element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.removeLast();
            assertListEmpty(list);
        });

        runTest("removes the last element from a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.removeLast();
            assertTrue(list.getFirst() == string1);
            assertTrue(list.getLast() == string2);
            assertTrue(list.getSize() == 2);
        });
    }

    /**
     * test the setHead method
     */
    @Test
    public void setHead() {
        runTest("throws exception for empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(string1), NoSuchElementException.class, "setFirst");
            assertListEmpty(list);
        });

        runTest("sets the data on the first element in a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.set(string2);
            assertTrue(list.getFirst() == string2);
            assertTrue(list.getLast() == string2);
        });

        runTest("sets first in a multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.set("str4");
            assertTrue(list.getFirst().equals("str4"));
            assertTrue(list.get(2) == string2);
        });
    }

    /**
     * test the set with Position method
     */
    @Test
    public void setPosition() {
        runTest("throws exception for index less than 1 on empty list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(string1, 0), NoSuchElementException.class);
            assertThrows(() -> list.set(string1, -1), NoSuchElementException.class);
        });

        runTest("throws exception for bad position on list with data", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            assertThrows(() -> list.set(string1, 0), NoSuchElementException.class);
            assertThrows(() -> list.set(string1, 4), NoSuchElementException.class);
        });

        runTest("sets position for single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.set("str4", 1);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str4"));
        });
        runTest("sets position for last element in multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.set("str4", 3);
            assertTrue(list.getSize() == 3);
            assertTrue(list.getLast().equals("str4"));
        });

        runTest("sets position for multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.set("str4", 2);
            assertTrue(list.getSize() == 3);
            assertTrue(list.get(2).equals("str4"));
        });
    }

    /**
     * test the set with Data method
     */
    @Test
    public void setData() {
        runTest("throws exception when data doesn't exist", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.set(string1, string2), NoSuchElementException.class);
        });

        runTest("sets data when there is a single element", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.set(string2, "str1");
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
            assertTrue(list.getSize() == 1);
        });
        runTest("sets position for multi-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string3);
            list.add(string2);
            list.add(string1);
            list.set("str4", "str2");
            assertTrue(list.getSize() == 3);
            assertTrue(list.getLast() == string3);
            assertTrue(list.get(2).equals("str4"));
        });
    }

    /**
     * test the setFirst method
     */
    @Test
    public void setFirst() {
        runTest("throws exception for emtpy list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.setFirst(string1), NoSuchElementException.class);
        });

        runTest("sets the head on a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.setFirst(string2);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
        });

        runTest("sets the head on a mutli-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.add(string1);
            list.setFirst(string3);
            assertTrue(list.getSize() == 2);
            assertTrue(list.getFirst() == string3);
            assertTrue(list.getLast() == string2);
        });

    }

    /**
     * test the setLast method
     */
    @Test
    public void setLast() {
        runTest("throws exception for emtpy list", () -> {
            LinkedList<String> list = new LinkedList<>();
            assertThrows(() -> list.setLast(string1), NoSuchElementException.class);
            assertListEmpty(list);
        });

        runTest("sets the head on a single element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string1);
            list.setLast(string2);
            assertTrue(list.getSize() == 1);
            assertTrue(list.getFirst().equals("str2"));
            assertTrue(list.getLast().equals("str2"));
        });

        runTest("sets the tail on a mutli-element list", () -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(string2);
            list.add(string1);
            list.setLast(string3);
            assertTrue(list.getSize() == 2);
            assertTrue(list.getLast() == string3);
            assertTrue(list.getFirst() == string1);
        });
    }


}
