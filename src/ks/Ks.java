package ks;

public class Ks {
    private static boolean isDirectory;
    private static boolean isFile;
    private static boolean showBenefit;
    private static boolean showRoom;
    private static boolean showTime;
    private static boolean showTaken;
    private static boolean solveGreedy;
    private static boolean solveMemoization;
    private static boolean solveTabulation;

    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("Error con los argumentos");
            System.exit(-1);
        }

        String directory = "", file = "";
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-d") || args[i].equals("--directory")) {
                isDirectory = true;
                directory = args[i+1];
            }
            if(args[i].equals("-f") || args[i].equals("--file")) {
                isFile = true;
                file = args[i+1];
            }

            showBenefit = (args[i].equals("-b") || args[i].equals("--benefit"));
            showRoom = (args[i].equals("-r") || args[i].equals("--room"));

            showTime = (args[i].equals("-t") || args[i].equals("--time"));

            showTaken = (args[i].equals("-dt") || args[i].equals("--display_taken"));

            solveGreedy = (args[i].equals("-sg") || args[i].equals("--greedy"));
            solveMemoization = (args[i].equals("-sm") || args[i].equals("--memoization"));
            solveTabulation = (args[i].equals("-st") || args[i].equals("--tabulation"));
        }

        if(isDirectory) {
            withDirectory(directory);
        } else if(isFile){
            withFile(file);
        } else {
            System.out.println("Introduzca un fichero o directorio");
            System.exit(-1);
        }

    }

    private static void withDirectory(String directory) {
        DataReader dr = new DataReader();
        String[] files = dr.readAllFiles(directory);
        for(String file: files) {
            withFile(file);
        }
    }

    private static void withFile(String file) {
        DataReader dr = new DataReader();
        Item[] items = dr.readFile(file);
        int capacity =  dr.getCapacity();

        long t0 = 0, t1 = 0;
        if(solveGreedy) {
            t0 = System.currentTimeMillis();
            // llamada al metodo
            t1 = System.currentTimeMillis();
        } else if(solveMemoization) {
            t0 = System.currentTimeMillis();
            // llamada al metodo
            t1 = System.currentTimeMillis();
        } else if(solveTabulation) {
            t0 = System.currentTimeMillis();
            // llamada al metodo
            t1 = System.currentTimeMillis();
        } else {
            System.out.println("Introduzca un método");
            System.exit(-1);
        }

        if(showBenefit) {
            System.out.println("Benefit = ");
        }
        if(showRoom) {
            System.out.println("Room = ");
        }
        if(showTime) {
            double time = (t1 - t0) / 1000;
            System.out.println("Time = " + time + "s");
        }
        if(showTaken) {
            System.out.println("Taken items = ");
        }
    }

}
