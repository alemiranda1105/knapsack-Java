package ks;

import java.util.Arrays;

public class Utilities {

    // Obtener el valor total de la mochila
    public int get_total_value(Item[] items, int[] taken) {
        int value = 0;
        for(Item i: items) {
            if (taken[i.index] == 1) {
                value += i.value;
            }
        }
        return value;
    }

    // Peso total de los elementos escogidos
    public int get_total_weight(Item[] items, int[] taken) {
        int weight = 0;
        for(Item i: items) {
            if (taken[i.index] == 1) {
                weight += i.weight;
            }
        }
        return weight;
    }

    // Peso restante en la mochila
    public int get_left_weight(int capacity, Item[] items, int[] taken) {
        return capacity - get_total_weight(items, taken);
    }

    // Comprobación de la solucion
    public int check_solution(int capacity, Item[] items, int[] taken) {
        int weight = get_total_weight(items, taken);
        int value = get_total_value(items, taken);
        if (weight > capacity) {
            System.out.println("Solución incorrecta, se supera la capacidad de la mochila (capacity, weight): "
                    + capacity + ", " + weight);
            return 0;
        }
        return value;
    }

    // Devuelve el index de los elementos escogidos
    public int[] taken_items(Item[] items, int[] taken) {
        int[] result = new int[taken.length];
        for (int i = 0; i < items.length; i++) {
            if (taken[i] == 1) {
                result[i] = i;
            }
        }
        return result;
    }
}
