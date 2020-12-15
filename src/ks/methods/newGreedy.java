package ks.methods;

import ks.utilities.Item;

import java.util.*;

public class newGreedy {
    private int[] taken;
    private int value = 0;

    // Tupla auxiliar donde guardamos el ratio value/weight
    private class Ratio {
        int index;
        double vw;

        public Ratio(int index, double vw) {
            this.index = index;
            this.vw = vw;
        }
    }

    public void solve_greedy(Item[] items, int capacity) {
        quickSort(items, 0);

        int weight = 0;
        List<Ratio> rat = new ArrayList<>();

        for(Item i: items) {
            rat.add(new Ratio(i.index, i.value/i.weight));
        }
        Collections.sort(rat, (o1, o2) -> {
            Double d1 = o1.vw;
            Double d2 = o2.vw;
            return d1.compareTo(d2);
        });

        for(Ratio r: rat) {
            Item item = items[r.index];
            if(item.weight + weight <= capacity) {
                taken[item.index] = 1;
                weight += item.weight;
                value += item.value;
            }
        }
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return value;
    }

    public void quickSort(Item[] items, int flag) {
        qs(items, 0, items.length - 1, flag);
    }

    private void qs(Item[] arr, int start, int stop, int flag) {
        if (start < stop) {
            int pivotIndex = partitionRand(arr, start, stop, flag);

            qs(arr, start, pivotIndex, flag);
            qs(arr, pivotIndex, stop, flag);
        }
    }

    private int partitionRand(Item[] arr, int start, int stop, int flag) {
        Random rand = new Random();
        int randPivot = rand.nextInt((stop - start) + 1) + start;

        Item aux = arr[start];
        arr[start] = arr[randPivot];
        arr[randPivot] = aux;

        return partition(arr, start, stop, flag);
    }

    private int partition(Item[] arr, int start, int stop, int flag) {
        int pivot = start;
        int i = start + 1;

        for(int j = start + 1; j < (stop + 1); j++) {
            if(flag == 1) {
                if(arr[j].value < arr[pivot].value) {
                    Item aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                    i++;
                }
            } else {
                if(arr[j].value > arr[pivot].value) {
                    Item aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                    i++;
                }
            }
        }
        Item aux = arr[pivot];
        arr[pivot] = arr[i - 1];
        arr[i-1] = aux;
        pivot = i - 1;
        return pivot;
    }
}
