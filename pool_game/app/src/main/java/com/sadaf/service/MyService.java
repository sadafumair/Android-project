package com.sadaf.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sadaf.customviews.DrawView;
import com.sadaf.resizablerectangle.ResizableRectangleActivity;

public class MyService extends Service {
    DrawView floatingImage;

    /**
     * @param intent
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        floatingImage = new DrawView(this);
    }


    
}