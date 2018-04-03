package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class AsyncExample extends Activity {

    TextView statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);
try {
    File f = new File(getFilesDir(), "Hello.txt");
    f.exists();

    f.delete();

}catch(Exception e){}


        statusView = (TextView) findViewById(R.id.number);
        MyAsyncThread myThread = new MyAsyncThread();

        //pass data to other core
        myThread.execute("http://torunski.ca/CST2335_XML.xml" );
    }

    public class MyAsyncThread extends AsyncTask<String, Integer, String>
    {
        //Background thread  (other core)
        public String doInBackground(String ... args)
         {
            int counter = 0;
            for(String siteUrl: args)
            {
                try {

                    URL url = new URL(siteUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inStream = urlConnection.getInputStream();


                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput( inStream  , "UTF-8");

                    int eventType = xpp.getEventType();
                    while(eventType != XmlPullParser.END_DOCUMENT)
                    {
                        switch(eventType)
                        {
                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();
                                if(tagName.equals("AMessage"))
                                {
                                    String value = xpp.getAttributeValue(null, "message");
                                    Log.i("value:", value);
                                }
                                if(tagName.equals("Weather"))
                                {
                                    String outlook = xpp.getAttributeValue(null, "outlook");
                                    String windy = xpp.getAttributeValue(null, "windy");
                                    Log.i("windy:", windy);
                                    Log.i("outlook:", outlook);
                                }
                                Log.i("Found tag:", tagName);
                                break;
                            case XmlPullParser.TEXT:
                                String text = xpp.getText();

                                Log.i("Found text:", text);

                                break;
                        }
                        xpp.next();
                        eventType = xpp.getEventType();
                    }

                }catch (Exception mfe)
                {
                    Log.e("Exception:", mfe.getMessage());
                }


                //telling Android data is ready
                publishProgress( ++counter );
            }
            return "Finished downloading";
         }

        //update the gui:
        public void onProgressUpdate(Integer ... args)
        {
            statusView.setText("Downloaded:" + args[0] );
        }

        //Gui thread, computation is finished
        public void onPostExecute(String result )
        {
            statusView.setText(result);
        }
    }
}
