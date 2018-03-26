package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FragmentExample extends Activity {

    boolean isTablet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        isTablet = (findViewById(R.id.frame_layout) != null);

        Button submitButton = (Button)findViewById(R.id.fragment_submit);
        EditText inputText = (EditText)findViewById(R.id.fragment_input);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle infoPassed = new Bundle();
                infoPassed.putString("UserInput", inputText.getText().toString());

                if(isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    DetailFragment df = new DetailFragment();

                     df.setArguments(infoPassed);

                    ft.replace(R.id.frame_layout, df);
                    ft.addToBackStack("Any string here"); //back button will unto transaction
                    ft.commit(); //put the fragment on the screen
                }
                else // for phone:
                {
                    Intent frameLayout = new Intent(FragmentExample.this, EmptyLayout.class);
                    frameLayout.putExtras(infoPassed);
                    startActivity(frameLayout);
                }
            }
        });
    }
}
