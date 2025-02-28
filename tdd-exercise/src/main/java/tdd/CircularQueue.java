package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     *
     * @return the fixed capacity of the {@link CircularQueue}
     */
    int getCapacity();

    /**
     * Adds a new value in the {@link CircularQueue}
     * @param value the value to add
     */
    void addElement(int value);

    /**
     *
     * @return true if the {@link CircularQueue} is empty, false otherwise
     */
    boolean isEmpty();

    /**
     *
     * @return the most recent element added in the {@link CircularQueue}
     */
    int getLastElement();

    /**
     * Removes the oldest element from the {@link CircularQueue}
     */
    void removeElement();
}