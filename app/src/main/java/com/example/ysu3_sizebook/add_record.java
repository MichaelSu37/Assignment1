package com.example.ysu3_sizebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class add_record extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Intent intent = getIntent();
        /*
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        */
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_add_record);
        //layout.addView(textView);

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


    public int saveRecord(){
        // need to check if name is empty and date in correct format
        EditText Name = (EditText) findViewById(R.id.enter_name);
        EditText Date = (EditText) findViewById(R.id.enter_date);

        EditText neck = (EditText) findViewById(R.id.enter_neck);
        EditText bust = (EditText) findViewById(R.id.enter_bust);
        EditText chest = (EditText) findViewById(R.id.enter_chest);
        EditText waist = (EditText) findViewById(R.id.enter_waist);
        EditText hip = (EditText) findViewById(R.id.enter_hip);
        EditText inseam = (EditText) findViewById(R.id.enter_inseam);
        EditText comment = (EditText) findViewById(R.id.enter_comment);


        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("Name", Name.getText().toString());
        spe.putString("Date", Date.getText().toString());
        spe.putString("neck", neck.getText().toString());
        spe.putString("bust", bust.getText().toString());
        spe.putString("chest", chest.getText().toString());
        spe.putString("waist", waist.getText().toString());
        spe.putString("hip", hip.getText().toString());
        spe.putString("inseam", inseam.getText().toString());
        spe.putString("comment", comment.getText().toString());
        spe.apply();


        /*
        retrieve
        SharedPreferences sp = getSharedPreferences("key", 0);
        String tValue = sp.getString("textvalue","");
        String tOperative = sp.getString("txtopertaive","");
         */
        return 0;
    }
}
