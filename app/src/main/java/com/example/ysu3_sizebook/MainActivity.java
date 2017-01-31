package com.example.ysu3_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class MainActivity extends ActionBarActivity {
    //public final static String EXTRA_MESSAGE = "com.example.sizebook.MESSAGE";
    public static final String FILENAME = "file.sav";
    private ArrayAdapter<Record> adapter;
    private ListView oldRecordList;
    private ArrayList<Record> recordList;
    private Record selectedRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldRecordList = (ListView) findViewById(R.id.list_of_record);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addRecord(View view){
        Intent intent = new Intent(this, add_record.class);
        startActivity(intent);

    }

    @Override
    protected void onStart(){
        super.onStart();

        loadFromFile();
        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, recordList);
        oldRecordList.setAdapter(adapter);




        oldRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRecord = (Record) adapterView.getSelectedItem();
            }


        });
    }







    public void loadFromFile(){
        try {
            FileInputStream inf = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(inf));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            recordList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        }
    }


    public void saveInFile() {
        try {
            FileOutputStream outf = openFileOutput(FILENAME, Context.MODE_APPEND);

            BufferedWriter out = new BufferedWriter((new OutputStreamWriter(outf)));

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
