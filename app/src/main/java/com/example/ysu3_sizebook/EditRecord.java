package com.example.ysu3_sizebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.security.PrivateKey;
import java.util.jar.Attributes;


// This class implements functions for edit record button
public class EditRecord extends AppCompatActivity {
    private EditText t1;
    private EditText t2;
    private EditText t3;
    private EditText t4;
    private EditText t5;
    private EditText t6;
    private EditText t7;
    private EditText t8;
    private EditText t9;

    private Intent resultIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        resultIntent = getIntent();
        Button saveButton = (Button) findViewById(R.id.save);


        t1 = (EditText) findViewById(R.id.enter_name);
        t2 = (EditText) findViewById(R.id.enter_date);
        t3 = (EditText) findViewById(R.id.enter_neck);
        t4 = (EditText) findViewById(R.id.enter_bust);
        t5 = (EditText) findViewById(R.id.enter_chest);
        t6 = (EditText) findViewById(R.id.enter_waist);
        t7 = (EditText) findViewById(R.id.enter_hip);
        t8 = (EditText) findViewById(R.id.enter_inseam);
        t9 = (EditText) findViewById(R.id.enter_comment);


        // setup onClick listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                String name = t1.getText().toString();
                if (!name.equals("")) {

                    // get data from text fields
                    String date = t2.getText().toString();
                    String neck = t3.getText().toString();
                    String bust = t4.getText().toString();
                    String chest = t5.getText().toString();
                    String waist = t6.getText().toString();
                    String hip = t7.getText().toString();
                    String inseam = t8.getText().toString();
                    String comment = t9.getText().toString();


                    resultIntent.putExtra("newName", name);
                    resultIntent.putExtra("newDate", date);
                    resultIntent.putExtra("newNeck", neck);
                    resultIntent.putExtra("newBust", bust);
                    resultIntent.putExtra("newChest", chest);
                    resultIntent.putExtra("newWaist", waist);
                    resultIntent.putExtra("newHip", hip);
                    resultIntent.putExtra("newInseam", inseam);
                    resultIntent.putExtra("newComment", comment);

                    // return results to parent activity
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    Context context = getApplicationContext();
                    PromptMessage pm = new PromptMessage(context, "Name cannot be empty!");
                    pm.showMessage();
                }
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        // read data from intent
        String name = resultIntent.getStringExtra("Name");
        String date = resultIntent.getStringExtra("Date");
        String neck = resultIntent.getStringExtra("Neck");
        String bust = resultIntent.getStringExtra("Bust");
        String chest = resultIntent.getStringExtra("Chest");
        String waist = resultIntent.getStringExtra("Waist");
        String hip = resultIntent.getStringExtra("Hip");
        String inseam = resultIntent.getStringExtra("Inseam");
        String comment = resultIntent.getStringExtra("Comment");

        t1.setText(name);
        t2.setText(date);
        t3.setText(neck);
        t4.setText(bust);
        t5.setText(chest);
        t6.setText(waist);
        t7.setText(hip);
        t8.setText(inseam);
        t9.setText(comment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_record, menu);
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
}
