package com.example.ysu3_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.util.Log;


public class AddRecord extends AppCompatActivity {
    public static final String FILENAME = "save.sav";
    public static final String TEMPFILE = "temp.sav";
    private ArrayList<Record> recordList;
    /*
    private String name;
    private String date ;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Intent intent = getIntent();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_record);

        Button saveButton = (Button) findViewById(R.id.save);

        //loadFromFile();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            FileOperation read = new FileOperation(recordList, in);
            recordList = read.loadFromFile();
            in.close();
        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                Context context = getApplicationContext();
                Record record = new Record();


                EditText t1 = (EditText) findViewById(R.id.enter_name);
                String name = t1.getText().toString();

                if (!name.equals("")) {

                    EditText t2 = (EditText) findViewById(R.id.enter_date);
                    EditText t3 = (EditText) findViewById(R.id.enter_neck);
                    EditText t4 = (EditText) findViewById(R.id.enter_bust);
                    EditText t5 = (EditText) findViewById(R.id.enter_chest);
                    EditText t6 = (EditText) findViewById(R.id.enter_waist);
                    EditText t7 = (EditText) findViewById(R.id.enter_hip);
                    EditText t8 = (EditText) findViewById(R.id.enter_inseam);
                    EditText t9 = (EditText) findViewById(R.id.enter_comment);


                    String date = t2.getText().toString();
                    String neck = t3.getText().toString();
                    String bust = t4.getText().toString();
                    String chest = t5.getText().toString();
                    String waist = t6.getText().toString();
                    String hip = t7.getText().toString();
                    String inseam = t8.getText().toString();
                    String comment = t9.getText().toString();

                    record.setName(name);
                    record.setDate(date);
                    record.setNeck(neck);
                    record.setBust(bust);
                    record.setChest(chest);
                    record.setWaist(waist);
                    record.setHip(hip);
                    record.setInseam(inseam);
                    record.setComment(comment);


                    recordList.add(record);

                    try {
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));
                        FileOperation write = new FileOperation(recordList, out);
                        write.saveInFile();
                        out.close();
                    } catch (FileNotFoundException e) {
                        recordList = new ArrayList<Record>();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
/*
                    recordList.add(record);
                    saveInFile();
*/

                    recordList = new ArrayList<>();
                    PromptMessage pm = new PromptMessage(context, "Record saved successfully");
                    pm.showMessage();
                    finish();
                }
                else{
                    PromptMessage pm = new PromptMessage(context, "Name cannot be empty!");
                    pm.showMessage();
                }
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));

            Gson gson = new Gson();
            gson.toJson(recordList, out);
            out.flush();

            out.close();
        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void loadFromFile(){
        try {
            FileInputStream inf = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(inf));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Record>>() {
            }.getType();
            recordList = gson.fromJson(in, listType);
            in.close();
        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
*/

}
