package ks.methods;

import ks.utilities.Item;

public class Memoization {
    private int[] taken;
    private int value = 0;

    public void memoization(Item[] items, int capacity) {
        this.taken = new int[items.length];
        int n = items.length;
        int w = capacity;
        int[][] mem;

        try {
            mem = new int[n + 1][w + 1];
        } catch (OutOfMemoryError e) {
            System.out.println("<<<<<ERROR DE MEMORIA(Capacity = " + capacity + ")>>>>>");
            return;
        }

        this.value = solved(items, mem, w, n);
        int i = items.length;
        int k = capacity;
        while(i > 0 && k > 0) {
            if(mem[i][k] != mem[i-1][k]) {
                taken[i-1] = 1;
                i--;
                k -= items[i].weight;
            } else {
                i--;
            }
        }
    }

    private int solved(Item[] items, int[][] mem, int w, int n) {
        if(n == 0 || w == 0) return 0;
        if(mem[n][w] != 0) return mem[n][w];

        Item item = items[n-1];
        if(w - item.weight >= 0) {
            mem[n][w] = Math.max(item.value + solved(items, mem,w - item.weight, n - 1), solved(items, mem, w, n - 1));
        } else {
            mem[n][w] = solved(items, mem, w, n - 1);
        }
        return mem[n][w];
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }
}
