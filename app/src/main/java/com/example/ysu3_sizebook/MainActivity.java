package com.example.ysu3_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

public class MainActivity extends ActionBarActivity {
    public static final String FILENAME = "save.sav";
    public static final String TEMPFILE = "temp.sav";

    private ArrayAdapter<Record> adapter;
    private Adapter intAdapter;
    private TextView recordLength;

    private ListView oldRecordList;
    private ArrayList<Record> recordList;
    private Record selectedRecord;
    private int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recordList = new ArrayList<>();
        oldRecordList = (ListView) findViewById(R.id.list_of_record);
        recordLength = (TextView) findViewById(R.id.totalnumber);

        oldRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {
                selectedRecord = (Record) adapter.getSelectedItem();
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
                    } catch (FileNotFoundException e) {
                        recordList = new ArrayList<Record>();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                    adapter.notifyDataSetChanged();


                }
                else{
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    CharSequence text = "Please select a record first!";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
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

    public void addRecord(View view){
        Intent intent = new Intent(this, add_record.class);
        startActivity(intent);

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
        this.length = recordList.size();
        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, recordList);
        oldRecordList.setAdapter(adapter);

        recordLength.setText("Number of records:  "+length);

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
