package org.example.DataDrivenExample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Base_class {
    public static int[] merge_arrays(int[] array1, int[] array2){
        int i,j,index = 0;
        int[] array3 = new int[array1.length+array2.length];

        for(i=0; i < array1.length; i++){
            array3[index] = array1[i];
            index++;
        }

        for(j=0; j < array2.length; j++){
            array3[index] = array2[j];
            index++;
        }
        
        return array3;
        
    }

    public static void write_file(int[] array) throws IOException {
        FileWriter fw = new FileWriter("ids.csv");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        for (int i = 0; i < array.length; i++){
            bw.write(array[i]+ "\t"+ "hello");
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    public static void read_file(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
       // List<String> x_values = new ArrayList<>();

        while ((line = br.readLine()) != null)
        {
            String[] a = line.split("\t");
            System.out.println(String.valueOf(a));
            //x_values.add(String.valueOf(a));
           // br.close();

        }

    }
}
