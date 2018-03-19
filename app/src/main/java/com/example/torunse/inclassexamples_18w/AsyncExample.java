package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncExample extends Activity {

    TextView numberShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);
        numberShow = (TextView)findViewById(R.id.number);

        WebInformationTask myBackgroundThread = new WebInformationTask();
        //Start the thread:
        myBackgroundThread.execute("http://torunski.ca/CST2335_XML.xml"); //T1 parameter
    }


    //this is the background thread object:
    public class WebInformationTask extends AsyncTask<String, Integer, String>
    {

        //for computation and network connections, background thread:
        public String doInBackground(String ... arg)
        {
            try {
                for(String siteUrl: arg) {
                    URL url = new URL(siteUrl);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream iStream = urlConnection.getInputStream();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput( iStream  , "UTF-8");

//start reading:
                    int type;
                    //While you're not at the end of the document:
                    while((type = xpp.getEventType()) != XmlPullParser.END_DOCUMENT)
                    {
                        //Are you currently at a Start Tag?
                        if(xpp.getEventType() == XmlPullParser.START_TAG)
                        {
                            //Is it the "AMessage" tag?
                            if(xpp.getName().equals("AMessage") )
                            {
                                String value =xpp.getAttributeValue(null, "message");
                                Log.i("XML Message:" , value );
                            }
                            //Is it the Weather tag?
                            else if(xpp.getName().equals("Weather") )
                            {
                                FileOutputStream of = openFileOutput("XMLData", Context.MODE_PRIVATE);
                                String value =xpp.getAttributeValue(null, "outlook");
                                Log.i("XML outlook:" , value );

                                value = xpp.getAttributeValue(null, "windy");
                                Log.i("XML windy:" , value );
                            }
                        }
                        // Go to the next XML event
                        xpp.next();
                    }
                }
            }catch (Exception mfe)
            {
                Log.e("Error", mfe.getMessage());
            }
            //Send a string to the GUI thread through onPostExecute
            return "Finished";

        }

        //when GUI is ready, update the objects
        public void onProgressUpdate(Integer ... data)
        {
            numberShow.setText("The number is now:" + data[0] );
        }

        //gui thread
        public void onPostExecute(String result)
        {
            //now you can post your result to the GUI
            numberShow.setText("Finished running thread" );
        }
    }
}
