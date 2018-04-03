package com.example.torunse.inclassexamples_18w;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NavigationExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);

        Toolbar tBar = (Toolbar)findViewById(R.id.nav_toolbar);
        setSupportActionBar(tBar);


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
            case R.id.menu_item1:
                //show a Toast
                Toast.makeText(this, "Clicked item 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                LayoutInflater inflater = getLayoutInflater();

                View extraStuff = inflater.inflate(R.layout.navigation_extra_items, null);
                Button clickButton = (Button)extraStuff.findViewById(R.id.submit_button);
                EditText input = (EditText)extraStuff.findViewById(R.id.extra_text);
                clickButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inText = input.getText().toString();
                        Toast.makeText(NavigationExample.this, inText, Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("This is the message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Accept
                                }
                        })
                        .setView(extraStuff) //adds extra items to box

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Cancel

                            }
                        })
                        .setNeutralButton("maybe", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                // What to do on Neutral button

                            }
                        });
                builder.create().show();

                break;
            case R.id.menu_item3:
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.worldwide)
                                .setAutoCancel(true)

                                .setContentTitle("Any title string")
                                .setContentText("Hello World!");
                    Intent nextActivity = new Intent(this, SQLActivity.class);

                PendingIntent resultPendingIntent = PendingIntent.getActivity( this, 0, nextActivity, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);

                //post the notification:
                int mNotificationId = 1;
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());

                break;
        }
        return true;
    }

}
