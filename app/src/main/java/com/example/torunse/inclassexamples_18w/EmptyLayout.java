package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class EmptyLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_layout);//the screen will be pink

Bundle infoToPass = getIntent().getExtras();

        //tablet click handler code:
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DetailFragment df  =  new DetailFragment();
        df.setArguments( infoToPass );
        ft.addToBackStack("Any name, not used"); //only undo FT on back button
        ft.replace(  R.id.space_for_later , df);
        ft.commit();

    }
}
