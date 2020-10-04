package com.example.demo.Utils;

import android.content.Context;
import android.util.Log;

import com.example.demo.Classes.CodiPostal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CodisPostalsManager {

    private static final String TAG = "CodisPostalsManager";
    private static final String filename = "codispostals.csv";
    public static final String FILE_REGEX = ";";
    public static ArrayList<CodiPostal> codisPostals;


    public static ArrayList<CodiPostal> searchCodes(ArrayList<CodiPostal> cps, String code) {
        ArrayList<CodiPostal> codiPostalsFound = new ArrayList<>();
        CodiPostal codiPostalFound = null;
        for (int i = 0; i < cps.size(); i++) {
            if (cps.get(i).getCodiPostal().contains(code)) {
                codiPostalFound = cps.get(i);
                codiPostalsFound.add(codiPostalFound);
            }
        }
        return codiPostalsFound;
    }

    public static CodiPostal getCode(ArrayList<CodiPostal> cps, String code) {
        CodiPostal codiPostalFound = null;
        for (int i = 0; i < cps.size(); i++) {
            if (cps.get(i).getCodiPostal().equals(code)) {
                codiPostalFound = cps.get(i);
            }
        }
        return codiPostalFound;
    }

    public static void readFile(Context context) {
        codisPostals = new ArrayList<>();
        BufferedReader br;
        CodiPostal cp = null;
        String path = checkFile(context);
        try {
            br = new BufferedReader(new FileReader(path));
            String line;
            String[] values;
            while ((line = br.readLine()) != null) {
                values = line.split(FILE_REGEX);
                cp = new CodiPostal(values[0], values[1], values[2], values[3]);
                codisPostals.add(cp);
            }

            Log.d(TAG, codisPostals.size() + " postal codes loaded!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String checkFile(Context context) {
        //check if directory exists first
        File dir = new File(context.getFilesDir(), "codis_postals");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = null;
        try {
            file = new File(dir, filename);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    Log.d(TAG, "checkFile: " + filename + " created, path: " + file.getAbsolutePath());
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                    for (CodiPostal cp : getCodisPostals()) {
                        writer.write(cp.toString());
                        writer.newLine();
                    }

                    writer.flush();
                    writer.close();

                } else {
                    Log.d(TAG, "checkFile: " + filename + " couldn't be created, path: " + file.getAbsolutePath());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

    //get codis postals from api
    public static ArrayList<CodiPostal> getCodisPostals() {
        return codisPostals;
    }
}
