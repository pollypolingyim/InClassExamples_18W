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

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ctx = this;

        Button insertButton = (Button)findViewById(R.id.insert_button);
        MyOpenHelper myOpener = new MyOpenHelper();
        SQLiteDatabase db = myOpener.getWritableDatabase();
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText priceField = (EditText)findViewById(R.id.sql_price);
                EditText nameFIeld = (EditText)findViewById(R.id.sql_name);
                ListView resultsList = (ListView)findViewById(R.id.database_list);


                ContentValues newData = new ContentValues();
                newData.put(MyOpenHelper.PRICE_COLUMN, priceField.getText().toString());
                newData.put(MyOpenHelper.NAME_COLUMN, nameFIeld.getText().toString());

                //Insert the data:
                db.insert(MyOpenHelper.TABLE_NAME, null, newData);

                Cursor results = db.query(false, MyOpenHelper.TABLE_NAME,
                        new String[] { "_id", MyOpenHelper.PRICE_COLUMN, MyOpenHelper.NAME_COLUMN},
                        "PRICE > ? ", new String [] {"500"},
                        null, null, null, null);

                int numResults = results.getCount(); //how many results
                results.moveToFirst();

                for(int i = 0; i < numResults; i++) {
                    int priceColumnIndex = results.getColumnIndex(MyOpenHelper.PRICE_COLUMN);
                    int nameColumnIndex = results.getColumnIndex(MyOpenHelper.NAME_COLUMN);

                    String resultName = results.getString(nameColumnIndex);
                    int restultPrice = results.getInt(priceColumnIndex);

                    results.moveToNext();
                }

                results.moveToFirst();  //reset the cursor
try {
    SimpleCursorAdapter listAdapter =
            new SimpleCursorAdapter(ctx, R.layout.sql_list_row, results,
                    new String[]{MyOpenHelper.NAME_COLUMN, MyOpenHelper.PRICE_COLUMN},
                    new int[]{R.id.row_name, R.id.row_price}, 0);

    resultsList.setAdapter(listAdapter); //pop;ulate the list with results\}
}catch(Exception e)
{
    Log.e("Crash!", e.getMessage());
}
            }
        });

    }

    public class MyOpenHelper extends SQLiteOpenHelper
    {
        public static final String DATABASE_NAME = "Filename.db";
        public static final int VERSION_NUM = 3;
        public static final String TABLE_NAME = "Products";
        public static final String NAME_COLUMN = "Name";
        public static final String PRICE_COLUMN = "PRICE";

        public MyOpenHelper()
        {
            super(ctx, DATABASE_NAME, null, VERSION_NUM);
        }
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME_COLUMN + " text, " + PRICE_COLUMN +  " INTEGER);" );
        }

        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) // newVer > oldVer
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME ); //delete any existing data
            onCreate(db);  //make a new database
        }
        public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) // newVer < oldVer
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME ); //delete any existing data
            onCreate(db);  //make a new database
        }

        public void onOpen(SQLiteDatabase db) //always gets called
        {
            Log.i("DATABASE", "Database opened");
        }
    }
}
