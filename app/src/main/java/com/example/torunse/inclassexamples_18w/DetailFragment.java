package com.example.torunse.inclassexamples_18w;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    String userText;
static int counter = 0;

    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        Bundle infoPassed = getArguments();

        userText = infoPassed.getString("UserInput");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View gui = inflater.inflate(R.layout.fragment_layout, null);
        TextView tv =(TextView) gui.findViewById(R.id.fragment_to);
        tv.setText("To:" + userText);
        return gui;
    }
}
