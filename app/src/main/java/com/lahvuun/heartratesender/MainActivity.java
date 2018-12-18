package com.lahvuun.heartratesender;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends WearableActivity {
    public static final String EXTRA_HOST_MESSAGE = "com.lahvuun.heartratesender.HOST_MESSAGE";
    public static final String EXTRA_PORT_MESSAGE = "com.lahvuun.heartratesender.PORT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connect(v);
            }
        });
        // Enables Always-on
        //setAmbientEnabled();
    }

    public void connect(View view) {
        Intent intent = new Intent(this, HRReadingsActivity.class);
        EditText hostText = findViewById(R.id.hostText);
        EditText portText = findViewById(R.id.portText);
        String hostMessage = hostText.getText().toString();
        int portMessage = Integer.parseInt(portText.getText().toString());
        intent.putExtra(EXTRA_HOST_MESSAGE, hostMessage);
        intent.putExtra(EXTRA_PORT_MESSAGE, portMessage);
        startActivity(intent);
    }
}
