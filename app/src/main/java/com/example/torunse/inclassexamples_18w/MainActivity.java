package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        Button helloButton = (Button)findViewById(R.id.hello_button);


        EditText textField = (EditText)findViewById(R.id.edittext);
        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   Intent nextScreen = new Intent(ctx, SecondActivity.class);
                   nextScreen.putExtra("NameKey", "Monday");
                   startActivityForResult(nextScreen, 5);
                }
                catch (NullPointerException npe)
                {

                }
            }
        });

    }
    @Override
    public void onStart()    {
        super.onStart();    }
    @Override
    public void onResume()    {
        super.onResume();    }

        public void onActivityResult(int req, int res, Intent data)
        {
            if(res == 55)
            {
                res = 55;
            }
            else
            {
                res = 0;
            }
        }
}
