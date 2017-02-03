package com.example.ysu3_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "save.sav";
    public static final String TEMPFILE = "temp.sav";

    private ArrayAdapter<Record> adapter;
    private TextView recordLength;

    private ListView oldRecordList;
    private ArrayList<Record> recordList;
    private Record selectedRecord;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //recordList = new ArrayList<>();
        context = getApplicationContext();
        oldRecordList = (ListView) findViewById(R.id.list_of_record);
        recordLength = (TextView) findViewById(R.id.totalnumber);

        oldRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                selectedRecord = (Record) adapter.getItemAtPosition(position);
                if (selectedRecord == null){
                    Log.d("debug", "not selected");
                }
            }


        });

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                if (selectedRecord != null){
                    recordList.remove(selectedRecord);

                    try {
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));
                        FileOperation fw = new FileOperation(recordList, out);
                        fw.saveInFile();
                        out.close();
                    } catch (FileNotFoundException e) {
                        recordList = new ArrayList<Record>();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }

                    selectedRecord = null;
                    recordLength.setText("Number of records:  " + recordList.size());
                    adapter.notifyDataSetChanged();
                }
                else{
                    //Context context = getApplicationContext();
                    PromptMessage pm = new PromptMessage(context, "Please select a record first!");
                    pm.showMessage();
                }
            }
        });
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


    @Override
    protected void onStart(){
        super.onStart();
        try {
            FileInputStream inf = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(inf));
            FileOperation fo = new FileOperation(recordList, in);
            recordList = fo.loadFromFile();

        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        }
        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, recordList);
        oldRecordList.setAdapter(adapter);

        recordLength.setText("Number of records:  " + recordList.size());
    }

    public void addRecord(View view){
        Intent intent = new Intent(this, AddRecord.class);
        startActivity(intent);
    }

    public void showDetail(View view){
        if (selectedRecord == null){
            //Context context = getApplicationContext();
            PromptMessage pm = new PromptMessage(context, "Please select a record first!");
            pm.showMessage();
        }
        else {
            String detail = "Detail of this record: \n" +
                    "Name: " + selectedRecord.getName() + "\n" +
                    "Date: " + selectedRecord.getDate() + "\n" +
                    "Neck: " + selectedRecord.getNeck() + "\n" +
                    "Bust: " + selectedRecord.getBust() + "\n" +
                    "Chest: " + selectedRecord.getChest() + "\n" +
                    "Waist: " + selectedRecord.getWaist() + "\n" +
                    "Hip: " + selectedRecord.getHip() + "\n" +
                    "Inseam: " + selectedRecord.getInseam() + "\n" +
                    "Comment: " + selectedRecord.getComment();

            Intent intent = new Intent(this, ViewDetail.class);
            intent.putExtra("Detail", detail);
            startActivity(intent);

        }

        selectedRecord = null;
    }


    public void editRecord(View view){
        if (selectedRecord == null){
            //Context context = getApplicationContext();
            PromptMessage pm = new PromptMessage(context, "Please select a record first!");
            pm.showMessage();
        }
        else{
            String name = selectedRecord.getName();
            String date = selectedRecord.getDate();
            String neck = selectedRecord.getNeck();
            String bust = selectedRecord.getBust();
            String chest = selectedRecord.getChest();
            String waist = selectedRecord.getWaist();
            String hip = selectedRecord.getHip();
            String inseam = selectedRecord.getInseam();
            String comment = selectedRecord.getComment();

            Intent intent = new Intent(this, EditRecord.class);
            intent.putExtra("Name", name);
            intent.putExtra("Date", date);
            intent.putExtra("Neck", neck);
            intent.putExtra("Bust", bust);
            intent.putExtra("Chest", chest);
            intent.putExtra("Waist", waist);
            intent.putExtra("Hip", hip);
            intent.putExtra("Inseam", inseam);
            intent.putExtra("Comment", comment);

            startActivityForResult(intent, 1);



        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String newName = data.getStringExtra("newName");
                String newDate = data.getStringExtra("newDate");
                String newNeck = data.getStringExtra("newNeck");
                String newBust = data.getStringExtra("newBust");
                String newChest = data.getStringExtra("newChest");
                String newWaist = data.getStringExtra("newWaist");
                String newHip = data.getStringExtra("newHip");
                String newInseam = data.getStringExtra("newInseam");
                String newComment = data.getStringExtra("newComment");

                recordList.remove(selectedRecord);
                Record record = new Record();
                record.setName(newName);
                record.setDate(newDate);
                record.setNeck(newNeck);
                record.setBust(newBust);
                record.setChest(newChest);
                record.setWaist(newWaist);
                record.setHip(newHip);
                record.setInseam(newInseam);
                record.setComment(newComment);
                recordList.add(record);

                //adapter.notifyDataSetChanged();
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));
                    FileOperation fw = new FileOperation(recordList, out);
                    fw.saveInFile();
                    out.close();
                } catch (FileNotFoundException e) {
                    recordList = new ArrayList<Record>();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                selectedRecord = null;
            }
        }
    }


/*
    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter((new OutputStreamWriter(fos)));


        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    */

}
