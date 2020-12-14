package ks.methods;

import ks.utilities.Item;

public class Tabulation {
    private int[] taken;
    private int value = 0;

    public void tabulation(Item[] items, int capacity) {
        this.taken = new int[items.length];
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }
}
