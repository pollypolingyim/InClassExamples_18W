package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayListActivity extends Activity {

    private String items[] = new String[]{ "Layout Examples", "BaseAdapter examples",
            "SQL Example", "AsyncExample", "Fragment Example", "Navigation Example" ,
            "Navigation Drawer Example", "Sensor example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        ListView theList = (ListView)findViewById(R.id.the_list);

        ArrayAdapter<String> theData =
                new ArrayAdapter<String>(this, R.layout.array_layout, items);
        theList.setAdapter( theData );

        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        startActivity(new Intent(ArrayListActivity.this, MainActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent( ArrayListActivity.this, ListViewActivity.class  ));
                        break;
                    case 2:
                        startActivity(new Intent( ArrayListActivity.this, SQLActivity.class  ));

                        break;
                    case 3:
                        startActivity(new Intent(ArrayListActivity.this, AsyncExample.class));
                        break;
                    case 4:
                        startActivity(new Intent(ArrayListActivity.this, FragmentExample.class));
                        break;
                    case 5:
                        startActivity(new Intent(ArrayListActivity.this, NavigationExample.class));
                        break;

                    case 6:
                        startActivity(new Intent(ArrayListActivity.this, SlidingDrawerExample.class));
                        break;
                    case 7:
                        startActivity(new Intent(ArrayListActivity.this, SensorsExample.class));
                        break;
                }
            }
        });
    }
}
