package com.example.torunse.inclassexamples_18w;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class NavigationExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);

        Toolbar toolbar = (Toolbar)findViewById(R.id.navigation_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_item_1:
                Log.i("Menu 1 clicked:" , item.getTitle().toString());
                //show a Toast
                break;
            case R.id.menu_item_2:
                Log.i("Menu 2 clicked:" , item.getTitle().toString());
                LayoutInflater inflater = NavigationExample.this.getLayoutInflater();
                View moreButtons = inflater.inflate(R.layout.dialog_layout, null);
                CheckBox cb = (CheckBox)moreButtons.findViewById(R.id.checkBox);
                Button btn = (Button)moreButtons.findViewById(R.id.button);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("CHeckbox:", "Clicked");
                    }
                });
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("Clicked", "Dialog button");
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationExample.this);
                builder.setMessage("This is the message")
                        .setNeutralButton("Maybe", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Neutral","Neutral" );

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Positive","Positive" );
                            }
                         })
                        .setView(moreButtons) // add the extra buttons to the dialog box
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Cancel
                                Log.i("Negative","Negative" );
                                }
                        });
                builder.create().show();
                break;
            case R.id.menu_item_3:
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.startup) //change this
                                .setAutoCancel(true)
                                .setContentTitle("My notification")//change this
                                .setContentText("Hello World!");//change this
                Intent resultIntent = new Intent(this, FragmentExample.class); //change this
                PendingIntent resultPendingIntent = PendingIntent.getActivity( this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
//Post the notification
                int mNotificationId = 001;//change this
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());

                break;
        }
        return true;
    }

}
