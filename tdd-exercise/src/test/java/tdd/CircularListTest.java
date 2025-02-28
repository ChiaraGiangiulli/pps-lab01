package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularQueue queue;
    private static final int ELEMENT_TO_ADD = -1;

    @BeforeEach
    public void  beforeEach(){
        this.queue = new CircularQueueImpl();
    }
    @Test
    public void isInitiallyEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void canAddElement(){
        queue.addElement(ELEMENT_TO_ADD);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void canAddElementIfFull(){
        IntStream.range(0,queue.getCapacity()).forEach(value -> queue.addElement(value));
        queue.addElement(ELEMENT_TO_ADD);
        assertEquals(ELEMENT_TO_ADD, queue.getLastElement());
    }

    @Test
    public void canRemoveElement(){
        queue.addElement(ELEMENT_TO_ADD);
        assertFalse(queue.isEmpty());
        queue.removeElement();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void cannotRemoveElementIfEmpty(){
        assertTrue(queue.isEmpty());
        assertThrows(
                IllegalStateException.class,
                () -> queue.removeElement()
        );
    }
}
