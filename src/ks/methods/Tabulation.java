package ks.methods;

import ks.utilities.Item;

public class Tabulation {
    private int[] taken;
    private int value = 0;

    public void tabulation(Item[] items, int capacity){
        int i = items.length;
        int k = capacity;

        taken = new int[items.length];
        int[][] table;
        try {
            table = new int[items.length+1][capacity+1];
        } catch (OutOfMemoryError e) {
            System.out.println("<<<<<ERROR DE MEMORIA(Capacity = " + capacity + ")>>>>>");
            return;
        }
        fillTable(table, items, capacity);

        while(i>0 && k>0){
            if(table[i][k] != table[i-1][k]){
                taken[i-1] = 1;
                value += items[i-1].value;
                i--;
                k -= items[i].weight;
            } else{
                i--;
            }
        }
    }

    private int[][] fillTable(int[][] table, Item[] items, int capacity) {
        for(int i = 1; i < (items.length+1); i++){
            for (int j = 0; j < (capacity+1); j++){
                if(items[i-1].weight <= j){
                    table[i][j] = Math.max(items[i - 1].value + table[i - 1][j - items[i - 1].weight], table[i - 1][j]);
                } else{
                    table[i][j] = table[i-1][j];
                }
            }
        }
        return table;
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }
}
