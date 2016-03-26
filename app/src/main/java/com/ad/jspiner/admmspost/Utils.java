package com.ad.jspiner.admmspost;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ad.jspiner.admmspost.Models.MenuModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by tpdbs on 2016-03-26.
 */
public class Utils {
    public Context mContect;
    private RequestQueue queue;

    public Utils(Context context) {
        mContect = context;
        queue = Volley.newRequestQueue(mContect);
    }


    public void RequestGET(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                        }
                        catch (Exception e){}

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mContect);
        requestQueue.add(stringRequest);
    }
}
