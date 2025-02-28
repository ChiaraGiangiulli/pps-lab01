package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue = new ArrayList<>();
    private static final int CAPACITY = 5;
    private static final int OLDEST_ELEMENT_INDEX = 0;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public void addElement(int value) {
        if(queue.size() < CAPACITY) queue.add(value);
        else {
            queue.remove(OLDEST_ELEMENT_INDEX);
            queue.add(value);
        }
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int getLastElement() {
        if (queue.isEmpty()) throw new IllegalStateException("Empty queue");
        final int lastElementIndex = queue.size()-1;
        return queue.get(lastElementIndex);
    }

    @Override
    public void removeElement() {
        if (queue.isEmpty()) throw new IllegalStateException("Empty queue");
        queue.remove(OLDEST_ELEMENT_INDEX);
    }
}
