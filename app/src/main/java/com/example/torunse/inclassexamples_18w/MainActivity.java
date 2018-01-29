package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button helloButton = (Button)findViewById(R.id.hello_button);


        EditText textField = (EditText)findViewById(R.id.editText);
        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    textField.setText("You clicked the button");
                }
                catch (NullPointerException npe)
                {

                }
            }
        });

    }
}
