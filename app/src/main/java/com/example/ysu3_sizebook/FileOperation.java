package com.example.ysu3_sizebook;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ysu3 on 2/1/17.
 */
public class FileOperation{
    private BufferedReader in;
    private BufferedWriter out;
    private ArrayList<Record> recordList;

    public FileOperation(ArrayList<Record> recordArrayList, BufferedReader in){
        this.in = in;
        recordList = recordArrayList;

    }

    public FileOperation(ArrayList<Record> recordArrayList, BufferedWriter out){
        this.out = out;
        recordList = recordArrayList;

    }

    public ArrayList<Record> loadFromFile() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Record>>() {
        }.getType();
        recordList = gson.fromJson(in, listType);

        return recordList;
    }


    public void saveInFile() throws IOException {

        Gson gson = new Gson();
        gson.toJson(recordList, out);
        out.flush();
    }
}
