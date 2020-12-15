package ks.application;

import ks.methods.Greedy;
import ks.methods.Memoization;
import ks.methods.Tabulation;
import ks.methods.newGreedy;
import ks.utilities.DataReader;
import ks.utilities.Item;
import ks.utilities.Utilities;

public class Solution {
    private final boolean isDirectory;
    private final boolean isFile;
    private final boolean showBenefit;
    private final boolean showRoom;
    private final boolean showTime;
    private final boolean showTaken;
    private final boolean solveGreedy;
    private final boolean solveMemoization;
    private final boolean solveTabulation;

    private String file = "";
    private String directory = "";

    public Solution(boolean isDirectory, boolean isFile, boolean showBenefit, boolean showRoom, boolean showTime, boolean showTaken, boolean solveGreedy, boolean solveMemoization, boolean solveTabulation) {
        this.isDirectory = isDirectory;
        this.isFile = isFile;
        this.showBenefit = showBenefit;
        this.showRoom = showRoom;
        this.showTime = showTime;
        this.showTaken = showTaken;
        this.solveGreedy = solveGreedy;
        this.solveMemoization = solveMemoization;
        this.solveTabulation = solveTabulation;
    }

    public void solve() {
        if(isDirectory) {
            withDirectory(directory);
        } else if(isFile){
            withFile(file);
        } else {
            System.out.println("Introduzca un fichero o directorio");
            System.exit(-1);
        }
    }

    private void withDirectory(String directory) {
        DataReader dr = new DataReader();
        String[] files = dr.readAllFiles(directory);
        double t0 = System.currentTimeMillis();
        for(String file: files) {
            System.out.println("----------" + file + "----------");
            withFile(directory + file);
        }
        double t1 = System.currentTimeMillis();
        double time = (t1 - t0) / 1000;
        System.out.println("<<<< END >>>>");
        System.out.println("Total execution time = " + time + "s");
    }

    private void withFile(String file) {
        DataReader dr = new DataReader();
        Item[] items = dr.readFile(file);
        int capacity =  dr.getCapacity();

        int value = 0;
        int[] taken = new int[items.length];

        double t0 = 0, t1 = 0;
        if(solveGreedy) {
            //Greedy gr = new Greedy();
            newGreedy gr = new newGreedy();
            t0 = System.currentTimeMillis();
            // llamada al metodo
            gr.solve_greedy(items, capacity);
            t1 = System.currentTimeMillis();
            value = gr.getValue();
            taken = gr.getTaken();
        } else if(solveMemoization) {
            Memoization mem = new Memoization();
            t0 = System.currentTimeMillis();
            // llamada al metodo
            mem.memoization(items, capacity);
            t1 = System.currentTimeMillis();
            value = mem.getValue();
            taken = mem.getTaken();
        } else if(solveTabulation) {
            Tabulation tb = new Tabulation();
            t0 = System.currentTimeMillis();
            // llamada al metodo
            tb.tabulation(items, capacity);
            t1 = System.currentTimeMillis();
            value = tb.getValue();
            taken = tb.getTaken();
        } else {
            System.out.println("Introduzca un método");
            System.exit(-1);
        }

        Utilities utilities = new Utilities();
        if(showBenefit) {
            System.out.println("Benefit = " + value);
        }
        if(showRoom) {
            System.out.println("Room = " + utilities.get_left_weight(capacity, items, taken));
        }
        if(showTaken) {
            int[] aux = utilities.taken_items(items, taken);
            if (aux.length <= 0) return;
            String res = "[";
            for (int i: aux) {
                if (i != 0) res += i + ", ";
            }
            res = (res.length() > 1 ? res.substring(0, res.lastIndexOf(", ")): "[");
            res += "]";
            System.out.println("Taken items = " + res);
        }
        if(showTime) {
            double time = (t1 - t0) / 1000;
            System.out.println("Time = " + time + "s");
        }
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
