package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

    int numItemsCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Button resetButton = (Button)findViewById(R.id.reset);

        ListView theList = (ListView)findViewById(R.id.the_list);
        MyNewAdapter adpt = new MyNewAdapter();


        //This line tells the listView which object has its data:
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // This is a callback function for when the user clickes on a row in the table:
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position is the position that was clicked on. id is the database item
                // of the item at position position. id is whatever your getItemId(int position) returns.
                Log.w("ListView clicked", "Position:" + position);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numItemsCount++;
                adpt.notifyDataSetChanged(); //cause the lisview to redraw everything
            }
        });
        theList.setAdapter( adpt );
    }

    private class MyNewAdapter extends BaseAdapter
    {
        //How many items to show:
        public int getCount()
        {
            return numItemsCount;
        }

        //How to display a row:
        public View getView(int position, View oldView, ViewGroup vg)
        {
            LayoutInflater inflater = getLayoutInflater();
            View newView;

            //view holder pattern. Reuse oldViews if possible:
            if(oldView == null)
                 newView = inflater.inflate(R.layout.table_cell_layout, vg, false );
            else
                newView = oldView;


            //Now set the elemtents of a row. First the left textView:
            TextView positionText = (TextView)newView.findViewById(R.id.number_place);
            //set the text:
            positionText.setText( getItem(position)  );

            TextView tv = (TextView)newView.findViewById(R.id.text_edit);

            tv.setText("This is item #" + getItem(position) );
            return newView;
        }

        public long getItemId(int position)
        {
            return position;
        }

            //What string at this position:
        public String getItem(int position)
        {
            return Integer.toString(position);
        }
    }
}
