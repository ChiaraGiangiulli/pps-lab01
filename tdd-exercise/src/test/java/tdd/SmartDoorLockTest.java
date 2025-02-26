package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoor;
    private static final int RIGHT_PIN = 1234;
    private static final int FIRST_DIGIT = 0;
    private static final int LAST_DIGIT = 4;
    private static final int WRONG_PIN = Integer.parseInt(Integer.toString(Math.abs(RIGHT_PIN + 1)).substring(FIRST_DIGIT,LAST_DIGIT));

    @BeforeEach
    void beforeEach(){
        smartDoor = new SmartDoor();
    }

    @Test
    public void isInitiallyOpen() {
        assertFalse(smartDoor.isLocked());
    }

    private void rightLock(){
        smartDoor.setPin(RIGHT_PIN);
        smartDoor.lock();
    }

    @Test
    public void canBeLocked() {
        rightLock();
        assertTrue(smartDoor.isLocked());
    }

    @Test
    public void testUnsettedPin(){
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> smartDoor.lock()
                ),
                () -> assertFalse(smartDoor.isLocked())
        );
    }

    @Test
    public void testInvalidPin(){
        int invalidPin = 123;
        assertThrows(
                IllegalArgumentException.class,
                () -> smartDoor.setPin(invalidPin)
        );
    }

    @Test
    public void canBeUnlocked() {
        rightLock();
        smartDoor.unlock(RIGHT_PIN);
        assertFalse(smartDoor.isLocked());
    }

    @Test
    public void testWrongPin(){
        rightLock();
        smartDoor.unlock(WRONG_PIN);
        assertTrue(smartDoor.isLocked());
    }

    private void blockDoor(){
        final int MAX_ATTEMPTS = smartDoor.getMaxAttempts();
        IntStream.range(0, MAX_ATTEMPTS).forEach(val -> smartDoor.unlock(WRONG_PIN));
    }

    @Test
    public void canBeBlocked(){
        rightLock();
        blockDoor();
        assertTrue(smartDoor.isBlocked());

    }

    @Test
    public void canBeUnblocked(){
        rightLock();
        blockDoor();
        assertTrue(smartDoor.isBlocked());
        smartDoor.reset();
        assertFalse(smartDoor.isBlocked());
    }

    @Test
    public void cannotBeUnlockedIfBlocked(){
        rightLock();
        blockDoor();
        assertThrows(
                IllegalStateException.class,
                () -> smartDoor.unlock(RIGHT_PIN));
    }

    @Test
    public void testReset(){
        blockDoor();
        assertTrue(smartDoor.isBlocked());
        smartDoor.reset();
        assertAll(
                () -> assertFalse(smartDoor.isLocked()),
                () -> assertFalse(smartDoor.isBlocked()),
                () -> assertEquals(0, smartDoor.getFailedAttempts()),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> smartDoor.lock()
                )
        );
    }

}
