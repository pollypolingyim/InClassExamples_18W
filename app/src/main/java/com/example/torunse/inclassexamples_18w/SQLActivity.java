package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SQLActivity extends Activity {
    Context ctx;
    protected static final String DATABASE_NAME = "Filename.db";
    protected static final int VERSION_NUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ctx = this;
        final EditText priceField = (EditText) findViewById(R.id.price);
        final EditText nameField = (EditText)findViewById(R.id.name);
        final ListView resultsList = (ListView)findViewById(R.id.database_list);

        Button insertButton = (Button)findViewById(R.id.insert_button);
        MyDatabaseOpener dbOpener = new MyDatabaseOpener();
        SQLiteDatabase db = dbOpener.getWritableDatabase();  // now start querying & writing

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues newData = new ContentValues();
                String price = priceField.getText().toString();
                newData.put(MyDatabaseOpener.priceColumn, price);

                String prodName = nameField.getText().toString();
                newData.put(MyDatabaseOpener.nameColumn, prodName);

                db.insert(MyDatabaseOpener.name, MyDatabaseOpener.priceColumn, newData);

                Cursor results = db.query(false, MyDatabaseOpener.name,
                        new String[ ]{ "_id", MyDatabaseOpener.priceColumn,MyDatabaseOpener.nameColumn },
                        " ? > ? ", new String[] {"PRICE","500"}, null, null, null, null);

                results.moveToFirst(); // go to first row

                while(!results.isAfterLast()) // is there more data?
                {
                    int priceIndex = results.getColumnIndex(MyDatabaseOpener.priceColumn);
                    int item_price = results.getInt(priceIndex);

                    int nameIndex = results.getColumnIndex(MyDatabaseOpener.nameColumn);
                    String productName = results.getString(nameIndex);

                    results.moveToNext(); //advance to next line
                }
                results.moveToFirst(); // go to first row
                SimpleCursorAdapter myCursorAdapter =
                        new SimpleCursorAdapter( ctx, R.layout.custom_cell1, results,
                        new String [] { MyDatabaseOpener.priceColumn, MyDatabaseOpener.nameColumn },
                        new int[] {R.id.text_place, R.id.row_button}, 0
                        );
                try {
                    resultsList.setAdapter(myCursorAdapter);
                }
                catch(Exception e)
                {
                    Log.e("Crash!", e.getMessage());
                }
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
