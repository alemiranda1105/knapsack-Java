package ks.application;

import ks.Solution;

public class Ks {

    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Error con los argumentos");
            System.exit(-1);
        }

        String directory = "", file = "";
        boolean isDirectory = false, isFile = false;
        boolean showBenefit = false, showRoom = false, showTime = false, showTaken = false;
        boolean solveGreedy = false, solveMemoization = false, solveTabulation = false;
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-d") || args[i].equals("--directory")) {
                isDirectory = true;
                directory = args[i+1];
            }
            if(args[i].equals("-f") || args[i].equals("--file")) {
                isFile = true;
                file = args[i+1];
            }
            if(args[i].equals("-b") || args[i].equals("--benefit")) {
                showBenefit = true;
            }
            if(args[i].equals("-r") || args[i].equals("--room")) {
                showRoom = true;
            }
            if(args[i].equals("-t") || args[i].equals("--time")) {
                showTime = true;
            }
            if(args[i].equals("-dt") || args[i].equals("--display_taken")) {
                showTaken = true;
            }
            if(args[i].equals("-sg") || args[i].equals("--greedy")) {
                solveGreedy = true;
            }
            if(args[i].equals("-sm") || args[i].equals("--memoization")) {
                solveMemoization = true;
            }
            if(args[i].equals("-st") || args[i].equals("--tabulation")) {
                solveTabulation = true;
            }
        }
        Solution sl = new Solution(isDirectory, isFile, showBenefit, showRoom, showTime, showTaken, solveGreedy, solveMemoization, solveTabulation);
        if(!file.equals("")) {
            sl.setFile(file);
        } else {
            sl.setDirectory(directory);
        }
        sl.solve();
    }

}
