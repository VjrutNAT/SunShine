package com.demo.vjrutnat.sunshine.SunShine;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by VjrutNAT on 12/29/2016.
 */

public class AppController extends Application {

    private static AppController appController;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static AppController newInstance() {
        return appController;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addRequestQueue(Request<T> reg, String tag) {
        reg.setTag(tag);
        getRequestQueue().add(reg);
    }

    public void cancelRequestQueue(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
