package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStack stack;
    private static final int VALUE = 1;
    private static final int ONE_ELEMENT_SIZE = 1;
    private static final int SIZE = 5;

    @BeforeEach
    public void beforeEach(){
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void isInitiallyEmpty(){
        assertTrue(stack.isEmpty());
    }

    @Test
    public void canPush(){
        stack.push(VALUE);
        assertAll(
                () -> assertFalse(stack.isEmpty()),
                () -> assertEquals(ONE_ELEMENT_SIZE, stack.size())
        );
    }

    @Test
    public void canPop(){
        stack.push(VALUE);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void canPeek(){
        stack.push(VALUE);
        stack.peek();
        assertAll(
                () -> assertFalse(stack.isEmpty()),
                () -> assertEquals(VALUE, stack.peek()),
                () -> assertEquals(ONE_ELEMENT_SIZE, stack.size())
        );
    }

    @Test
    public void cannotPopIfEmpty(){
        assertThrows(
                IllegalStateException.class,
                () -> stack.pop()
        );
    }

    @Test
    public void cannotPeekIfEmpty(){
        assertThrows(
                IllegalStateException.class,
                () -> stack.peek()
        );
    }

    @Test
    public void canGetMin(){
        final int min = VALUE;
        IntStream.range(0,SIZE).forEach(val -> stack.push(VALUE + val));
        assertEquals(stack.getMin(), min);
    }

    @Test
    public void canGetMax(){
        final int max = VALUE + SIZE - 1;
        IntStream.range(0,SIZE).forEach(val -> stack.push(VALUE + val));
        assertEquals(stack.getMax(), max);
    }

    @Test
    public void cannotGetMinIfEmpty(){
        assertThrows(
                IllegalStateException.class,
                () -> stack.getMin()
        );
    }

    @Test
    public void cannotGetMaxIfEmpty(){
        assertThrows(
                IllegalStateException.class,
                () -> stack.getMax()
        );
    }
}