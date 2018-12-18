package com.lahvuun.heartratesender;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingQueue;

public class HREventListener implements SensorEventListener {
    private static final String TAG = "HRReadingsActivity";
    private TextView mTextView;
    private LinkedBlockingQueue readingsQueue;

    public HREventListener(TextView textView, LinkedBlockingQueue queue) {
        mTextView = textView;
        readingsQueue = queue;
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            readingsQueue.offer((int)event.values[0]);
            String msg = "" + (int)event.values[0];
            mTextView.setText(msg);
            Log.d(TAG, msg);
        }
        else
            Log.d(TAG, "Unknown sensor type");
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }
}
