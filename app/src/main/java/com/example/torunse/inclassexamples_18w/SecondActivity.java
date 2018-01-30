package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button1 = (Button)findViewById(R.id.button_one);
        Button cancel = (Button)findViewById(R.id.button_cancel);
       String input = getIntent().getStringExtra("UserInput");

       button1.setText(input);
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setResult(66, new Intent());
               finish();
           }
       });

        cancel.setOnClickListener(e -> {
            setResult(88, new Intent());
            finish();
        });
    }
}
