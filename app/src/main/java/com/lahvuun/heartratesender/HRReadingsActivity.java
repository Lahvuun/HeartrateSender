package com.lahvuun.heartratesender;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingQueue;

public class HRReadingsActivity extends WearableActivity {
    SensorManager mSensorManager;
    HREventListener hrEventListener;
    LinkedBlockingQueue readingsQueue = new LinkedBlockingQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrreadings);

        hrEventListener = new HREventListener((TextView) findViewById(R.id.text), readingsQueue);
        getStepCount();

        Intent intent = getIntent();
        String hostMessage = intent.getStringExtra(MainActivity.EXTRA_HOST_MESSAGE);
        int portMessage = intent.getIntExtra(MainActivity.EXTRA_PORT_MESSAGE, 0);

        new Thread(new HRReadingsSender(readingsQueue, hostMessage, portMessage)).start();

        // Enables Always-on
        //setAmbientEnabled();
    }

    protected void onStop() {
        mSensorManager.unregisterListener(hrEventListener);
        super.onStop();
    }

    private void getStepCount() {
        mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(hrEventListener, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}