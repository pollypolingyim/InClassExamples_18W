package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

    int numItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        numItems = 1000;

        ListView theList = (ListView)findViewById(R.id.the_list);
        Button addButton = (Button)findViewById(R.id.reset);

        MyAdapter myCustomAdapter = new MyAdapter();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numItems++;

                //data updated:
                myCustomAdapter.notifyDataSetChanged();
            }
        });
        //Who has the data at the start:
        theList.setAdapter( myCustomAdapter );
    }

    private class MyAdapter extends BaseAdapter
    {
        //How much data:
        public int getCount() {
            return numItems;
        }
        //What to show at row: position
        public String getItem(int position)
        {
            return Integer.toString(position);
        }
        // How to arrange a row:
        public View getView(int position, View oldView, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();
            View createdRow = oldView;
            if(oldView == null)
               createdRow = inflater.inflate(R.layout.custom_cell1, null);

            TextView tv = (TextView)createdRow.findViewById(R.id.text_place);
            Button rowButton = (Button)createdRow.findViewById(R.id.row_button);
            rowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rowButton.setText( rowButton.getText().toString() +  tv.getText() );
                }
            });
           // if(oldView == null)
                tv.setText( getItem( position ));
            return createdRow;
        }

        //What is the database id of item at position:
        public long getItemId(int position)
        {
            return position;
        }
    }
}
