package com.example.torunse.inclassexamples_18w;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SensorsExample extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextView xView, yView, zView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make fullscreen:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_sensors_example);
        Button vButton = (Button)findViewById(R.id.vibrate_button);
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vr = (Vibrator) SensorsExample.this.getSystemService(Context.VIBRATOR_SERVICE);
                vr.vibrate(1000);
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        xView = (TextView)findViewById(R.id.x_field);
        yView = (TextView)findViewById(R.id.y_field);
        zView = (TextView)findViewById(R.id.z_field);
    }

    public void onAccuracyChanged(Sensor s, int val)
    {

    }

    public void onSensorChanged(SensorEvent evt)
    {
        float  []newVals = evt.values;
        int numDimensions = newVals.length;

        xView.setText("X:" + newVals[0]);
        yView.setText("Y:" + newVals[1]);
        zView.setText("Z:" + newVals[2]);

        if(newVals[1] < 0)
        {
            finish();
        }
    }
}
