package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button onlyButton = (Button)findViewById(R.id.button_one);

    String text=     getIntent().getStringExtra("NameKey");
    onlyButton.setText(text);

    onlyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }


    });
        Button cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(55);
                finish();
            }
        });
    }
}
