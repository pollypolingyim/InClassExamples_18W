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

    private EditText inputText;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        inputText = (EditText)findViewById(R.id.fragment_input);

        isTablet = (findViewById(R.id.space_for_later) != null);

        Button clickButton = (Button)findViewById(R.id.fragment_button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle infoToPass = new Bundle();
                infoToPass.putString("UserText", inputText.getText().toString());

                //if on tablet:
                if(isTablet)
                {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    DetailFragment df  =  new DetailFragment();
                    df.setArguments( infoToPass );
                    ft.addToBackStack("Any name, not used"); //only undo FT on back button
                    ft.replace(  R.id.space_for_later , df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(FragmentExample.this, EmptyLayout.class);
                    next.putExtras(infoToPass);
                    startActivity(next);
                }
            }
        });
    }
}
