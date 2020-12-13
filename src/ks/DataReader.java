package ks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    private int capacity = 0;

    public String[] readAllFiles(String path) {
        File directory = new File(path);
        String[] files = directory.list();
        return files;
    }

    public Item[] readFile(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] aux = line.split(" ");
            this.capacity = Integer.parseInt(aux[1]);
            Item[] items = new Item[Integer.parseInt(aux[0])];
            int i = 0;

            while((line = br.readLine()) != null) {
                aux = line.split(" ");
                if(i < items.length){
                    items[i] = new Item(i, Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));
                }
                i++;
            }
            return items;

        } catch (Exception e) {
            System.out.println("Error leyendo archivos");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

}
