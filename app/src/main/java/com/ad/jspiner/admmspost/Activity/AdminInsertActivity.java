package com.ad.jspiner.admmspost.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ad.jspiner.admmspost.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminInsertActivity extends Activity {
    public static final String TAG = AdminInsertActivity.class.getSimpleName();

    public static final String API_URL = "http://qwebmomo.cafe24.com/api/signup_admin.php";
    public static final String KEY_PHONE="id";
    public static final String KEY_NAME="name";
    public static final String KEY_PASS="pw";

    @Bind(R.id.btn_admim_insert) Button btn_insert;
    @Bind(R.id.admin_insert_phone) EditText phone;
    @Bind(R.id.admin_insert_name) EditText name;
    @Bind(R.id.admin_insert_pass) EditText pass;

    String phones;
    String names;
    String passs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert);
        init();
    }

    void init(){
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_admim_insert)
    void btn_admin_insert(){
        phones = phone.getText().toString().trim();
        names = name.getText().toString().trim();
        passs = pass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String code = obj.getString("code");
                            if(code.equals("1")){
                                Intent i = new Intent(AdminInsertActivity.this,AdminControlActivity.class);
                                startActivity(i);
                            }


                        }
                        catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminInsertActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_PHONE,phones);
                map.put(KEY_NAME,names);
                map.put(KEY_PASS,passs);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
