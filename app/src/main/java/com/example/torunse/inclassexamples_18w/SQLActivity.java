package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SQLActivity extends Activity {
    Context ctx;
    protected static final String DATABASE_NAME = "Filename.db";
    protected static final int VERSION_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ctx = this;

        Button insertButton = (Button)findViewById(R.id.insert_button);
        MyDatabaseOpener dbOpener = new MyDatabaseOpener();
        SQLiteDatabase db = dbOpener.getWritableDatabase();  // now start querying & writing

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues newData = new ContentValues();
                newData.put(MyDatabaseOpener.priceColumn, 499);
                newData.put(MyDatabaseOpener.nameColumn, "SpecialK");
                db.insert(MyDatabaseOpener.name, MyDatabaseOpener.priceColumn, newData);

                db.query(false, MyDatabaseOpener.name,
                        new String[ ]{MyDatabaseOpener.priceColumn,MyDatabaseOpener.nameColumn },
                        " ? > ? ", new String[] {"PRICE","500"}, null, null, null, null);
            }
        });
    }

    private class MyDatabaseOpener extends SQLiteOpenHelper
    {
        public static final String name = "Products";
        public static final String nameColumn = "NAME";
        public static final String priceColumn = "PRICE";

        public MyDatabaseOpener(  )
        {
            super(ctx, DATABASE_NAME, null, VERSION_NUM);
        }

        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE " + name + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + nameColumn + " text, " + priceColumn + " INTEGER);" );
        }

        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)    // newVer > oldVer
        {
            db.execSQL("DROP TABLE IF EXISTS " + name);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) //newVer < oldVer
        {
            db.execSQL("DROP TABLE IF EXISTS " + name);
            onCreate(db);
        }
        public void onOpen(SQLiteDatabase db) //always gets called
        {
            //load data here, SQL query:
        }
    }
}
