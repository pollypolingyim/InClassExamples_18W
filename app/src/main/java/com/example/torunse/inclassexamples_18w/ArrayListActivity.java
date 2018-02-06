package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.os.Bundle;

public class ArrayListActivity extends Activity {

    private String items[] = new String[]{ "A", "B" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
    }
}
