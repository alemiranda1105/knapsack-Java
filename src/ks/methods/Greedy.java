package ks.methods;

import ks.utilities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Greedy {
    private int[] taken;
    private int value = 0;

    public void solve_greedy(Item[] items, int capacity) {
        //0 -> mayor a menor / 1 -> menor a mayor
        quickSort(items, 1);
        this.taken = new int[items.length];
        int weight = 0;
        List<Double> vw = new ArrayList<>();

        for(int i = 0; i < items.length; i++){
            double res = items[i].value / items[i].weight;
            vw.add(res);
        }

        while (weight < capacity){
            if(vw.size() > 0){
                int best = max(vw.toArray());
                Item item = items[best];
                if ((item.weight + weight) <= capacity){
                    this.taken[item.index] = 1;
                    this.value += item.value;
                    weight += item.weight;
                }
                vw.remove(best);

            } else{
                break;
            }
        }
    }

    public void quickSort(Item[] items, int flag){
        qs(items, 0, items.length-1, flag);
    }

    private void qs(Item[] arr, int start, int stop, int flag) {
        if(start < stop){
            int pivotindex = partitionrand(arr, start, stop, flag);
            qs(arr, start, pivotindex-1, flag);
            qs(arr, pivotindex+1, stop, flag);
        }
    }

    private int partitionrand(Item[] arr, int start, int stop, int flag) {
        Random rand = new Random();
        int randpivot = rand.nextInt((stop - start) + 1) + start;
        Item aux = arr[start];
        arr[start] = arr[randpivot];
        arr[randpivot] = aux;

        return partition(arr, start, stop, flag);
    }

    private int partition(Item[] arr, int start, int stop, int flag) {
        int pivot = start;
        int i = start + 1 ;

        for(int j = i; j < (stop+1); j++){
            if(flag == 0){
                if(arr[pivot].value < arr[j].value) {
                    Item aux = arr[pivot];
                    arr[pivot] = arr[j];
                    arr[j] = aux;
                    i++;
                }
            } else if(flag == 1){
                if(arr[pivot].value > arr[j].value) {
                    Item aux = arr[pivot];
                    arr[pivot] = arr[j];
                    arr[j] = aux;
                    i++;
                }
            }
        }
        Item aux = arr[pivot];
        arr[pivot] = arr[i-1];
        arr[i-1] = aux;
        pivot = i-1;
        return pivot;
    }

    private int max(Object[] vw){
        double max = 0;
        int index = 0;
        for(int i = 0; i<vw.length; i++){
            if(max < (double) (vw[i])){
                max = (double) (vw[i]);
                index = i;
            }
        }
        return index;
    }

    public int[] getTaken() {
        return taken;
    }

    public int getValue() {
        return this.value;
    }
}
