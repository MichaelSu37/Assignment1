package com.example.ysu3_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.util.Log;


public class add_record extends ActionBarActivity {
    public static final String FILENAME = "file.sav";
    private ArrayList<Record> recordList;
    private Record rec = new Record();
    private String name;
    private String date ;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Intent intent = getIntent();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_record);


        Button saveButton = (Button) findViewById(R.id.save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                EditText t1 = (EditText) findViewById(R.id.enter_name);
                EditText t2 = (EditText) findViewById(R.id.enter_date);
                EditText t3 = (EditText) findViewById(R.id.enter_neck);
                EditText t4 = (EditText) findViewById(R.id.enter_bust);
                EditText t5 = (EditText) findViewById(R.id.enter_chest);
                EditText t6 = (EditText) findViewById(R.id.enter_waist);
                EditText t7 = (EditText) findViewById(R.id.enter_hip);
                EditText t8 = (EditText) findViewById(R.id.enter_inseam);
                EditText t9 = (EditText) findViewById(R.id.enter_comment);

                name = t1.getText().toString();
                date = t2.getText().toString();
                neck = t3.getText().toString();
                bust = t4.getText().toString();
                chest = t5.getText().toString();
                waist = t6.getText().toString();
                hip = t7.getText().toString();
                inseam = t8.getText().toString();
                comment = t9.getText().toString();

                rec.setName(name);
                rec.setDate(date);
                rec.setNeck(neck);
                rec.setBust(bust);
                rec.setChest(chest);
                rec.setWaist(waist);
                rec.setHip(hip);
                rec.setInseam(inseam);
                rec.setComment(comment);

                recordList.add(rec);

                saveInFile();
                finish();
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


    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);

            BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));

            Gson gson = new Gson();
            gson.toJson(recordList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
