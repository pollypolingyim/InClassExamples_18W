package com.example.torunse.inclassexamples_18w;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

public class SlidingDrawerExample extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);
        Toolbar tBar = (Toolbar)findViewById(R.id.nav_toolbar);

        setSupportActionBar(tBar);
        DrawerLayout dl = (DrawerLayout)findViewById(R.id.nav_drawer_example);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this,dl,  tBar,
                R.string.open_drawer, R.string.close_drawer);
        dl.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this); //call onNavigationItemSelected
    }

    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_item1:
Log.i("Breakpoint", "breakpoint");
                break;

            case R.id.nav_item2:
Log.i("Breakpoint", "Breakpoint");
                break;
        }
        DrawerLayout dl = (DrawerLayout)findViewById(R.id.nav_drawer_example);
        dl.closeDrawer(GravityCompat.START);  //hide the drawer
        return true;
    }
}
