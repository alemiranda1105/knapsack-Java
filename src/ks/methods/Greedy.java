package ks.methods;

import ks.utilities.Item;

public class Greedy {
    private int[] taken;
    private int value = 0;

    public void solve_greedy(Item[] items, int capacity) {
        this.taken = new int[items.length];
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }
}
