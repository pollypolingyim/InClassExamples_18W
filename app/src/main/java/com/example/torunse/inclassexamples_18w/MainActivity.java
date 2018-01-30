package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.hello_button);
        EditText textField = (EditText)findViewById(R.id.edittext);
        Button button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(MainActivity.this,
                        SecondActivity.class);
                secondIntent.putExtra("UserInput", textField.getText().toString());
                startActivityForResult(secondIntent, 275);
            }
        });

        button2.setOnClickListener( e ->
            {
            Intent secondIntent = new Intent(MainActivity.this,
                    ThirdActivity.class);
            startActivityForResult(secondIntent, 10);

            }
        );
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        if(resultCode == 66)
        {
            resultCode = 66;
        }
        else if (resultCode == 88)
        {
            resultCode = 88;
        }
    }
}
