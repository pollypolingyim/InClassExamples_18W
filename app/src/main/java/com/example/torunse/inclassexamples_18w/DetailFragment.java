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

    Context parent;
    String userText;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        Bundle getInfo = getArguments();
        userText = getInfo.getString("UserText");
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_layout, null);
        TextView tv =(TextView) gui.findViewById(R.id.fragment_to);
        tv.setText("To:" + userText);
        return gui;
    }
}
