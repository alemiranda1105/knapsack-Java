package ks.methods;

import ks.utilities.Item;

import java.util.HashMap;
import java.util.Map;

public class Memoization {
    private int[] taken;
    private int value = 0;

    public void memoization(Item[] items, int capacity) {
        this.taken = new int[items.length];
        int n = items.length;
        Map<String, Integer> mem = new HashMap<>();

        this.value = solved(items, mem, capacity, n);
        int i = items.length;
        int k = capacity;
        String key1 = (i + ": " + k);
        String key2 = ((i-1) + ": " + k);
        while(i > 0 && k > 0) {
            if(mem.containsKey(key1) && mem.containsKey(key2)) {
                if(!mem.get(key1).equals(mem.get(key2))) {
                    taken[i-1] = 1;
                    i--;
                    k -= items[i].weight;
                } else {
                    i--;
                }
            } else {
                i--;
            }
            key1 = (i + ": " + k);
            key2 = ((i-1) + ": " + k);
        }
    }

    private int solved(Item[] items, Map<String, Integer> mem, int w, int n) {
        if(n == 0 || w == 0) return 0;
        String key = (n + ": " + w);
        if(mem.containsKey(key)) return mem.get(key);
        else {
            Item item = items[n-1];
            if(w - item.weight >= 0) {
                mem.put(key, Math.max(item.value + solved(items, mem,w - item.weight, n - 1), solved(items, mem, w, n - 1)));
            } else {
                mem.put(key, solved(items, mem, w, n - 1));
            }
            return mem.get(key);
        }
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }
}
