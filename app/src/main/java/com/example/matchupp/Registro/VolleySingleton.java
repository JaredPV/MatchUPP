package com.example.matchupp.Registro;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context context){
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context){
        if(instance == null){
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
