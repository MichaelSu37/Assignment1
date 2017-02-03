package com.example.ysu3_sizebook;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ysu3 on 2/2/17.
 */
public class PromptMessage {
    private String message;
    private Context context;


    public PromptMessage(Context context, String msg){
        this.context = context;
        this.message = msg;
    }

    public void showMessage(){
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = message;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
