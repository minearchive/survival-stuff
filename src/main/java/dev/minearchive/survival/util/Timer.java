package dev.minearchive.survival.util;

public class Timer {
    long start;

    public Timer() {
        this.start = System.currentTimeMillis();
    }

    public boolean passed(long ms) {
        return System.currentTimeMillis() - start > ms;
    }

    public void reset() {
        start = System.currentTimeMillis();
    }

}
