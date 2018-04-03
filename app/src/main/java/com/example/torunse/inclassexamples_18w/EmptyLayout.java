package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class EmptyLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_layout);

        Bundle infoPassed = getIntent().getExtras();


        //copy and paste button code:
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DetailFragment df = new DetailFragment();

        df.setArguments(infoPassed);

        ft.replace(R.id.frame_layout, df);
   //     ft.addToBackStack("Any string here"); //back button will unto transaction
        ft.commit(); //put the fragment on the screen
    }
}
