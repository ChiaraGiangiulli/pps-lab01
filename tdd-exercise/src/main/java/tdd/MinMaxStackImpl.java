package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    private final List<Integer> stack = new ArrayList<>();

    @Override
    public void push(int value) {
        stack.add(value);
    }

    private int getLastIndex(){
        return stack.size()-1;
    }

    private void checkEmptyStack(){
        if (stack.isEmpty()) throw new IllegalStateException("Empty stack");
    }

    @Override
    public int pop() {
        checkEmptyStack();
        return stack.remove(getLastIndex());
    }

    @Override
    public int peek() {
        checkEmptyStack();
        return stack.get(getLastIndex());
    }

    @Override
    public int getMin() {
        checkEmptyStack();
        return stack.stream().min(Integer::compareTo).get();
    }

    @Override
    public int getMax() {
        checkEmptyStack();
        return stack.stream().max(Integer::compareTo).get();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
