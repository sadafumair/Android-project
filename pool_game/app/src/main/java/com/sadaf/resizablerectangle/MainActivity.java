package com.sadaf.resizablerectangle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sadaf.service.MyService;

public class MainActivity extends Activity implements View.OnClickListener {
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(this);// assigning click listener to the button
        stop.setOnClickListener(this); // assigning click listener to the button
    }

    /**
     * @params v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startService(new Intent(getApplication(),MyService.class)); // starting the service
                break;
            case R.id.stop:
                stopService(new Intent(getApplication(),MyService.class)); // stoping the service
                break;
        }
    }
}
