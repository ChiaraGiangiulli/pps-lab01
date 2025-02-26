package tdd;

public class SmartDoor implements SmartDoorLock{

    private static final int PIN_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 5;
    private static final int PIN_NOT_SET = 0;
    private int pin, failedAttempts;
    private boolean locked, blocked;

    public SmartDoor(){
        setUp();
    }

    private void setUp(){
        pin = PIN_NOT_SET;
        locked = false;
        blocked = false;
        failedAttempts = 0;
    }

    private boolean isPinValid(int pin){
        return (pin != PIN_NOT_SET) && String.valueOf(pin).length() == PIN_LENGTH;
    }

    @Override
    public void setPin(int pin) {
        if(locked || blocked) throw new IllegalStateException("Door locked or blocked");
        if(!isPinValid(pin)) throw new IllegalArgumentException("Invalid pin");
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (blocked) throw new IllegalStateException("Cannot unlock if the door is blocked");
        if (isPinValid(pin) && pin == this.pin) locked = false;
        else {
            failedAttempts++;
            if (this.getFailedAttempts() >= MAX_ATTEMPTS) blocked = true;
        }
    }

    @Override
    public void lock() {
        if(!isPinValid(this.pin)) throw new IllegalArgumentException("Pin not set");
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.setUp();
    }
}
