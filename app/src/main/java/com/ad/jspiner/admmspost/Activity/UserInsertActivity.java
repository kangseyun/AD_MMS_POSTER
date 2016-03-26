package com.ad.jspiner.admmspost.Activity;

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
import android.widget.Toast;

import com.ad.jspiner.admmspost.MainActivity;
import com.ad.jspiner.admmspost.Models.LoginModel;
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
import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInsertActivity extends AppCompatActivity {
    public static final String TAG = UserInsertActivity.class.getSimpleName();
    public static final String API_URL = "http://qwebmomo.cafe24.com/api/signup_user.php";

    public static final String KEY_PHONE="id";
    public static final String KEY_NAME="name";
    public static final String KEY_admin="admin_no";

    public LoginModel loginmodel;

    @Bind(R.id.user_insert_phone) EditText phone;
    @Bind(R.id.user_insert_name) EditText name;
    @Bind(R.id.user_insert_btn) Button btn_insert;


    String phones;
    String names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_insert);

        init();
    }

    void init(){
        ButterKnife.bind(this);
    }

    @OnClick(R.id.user_insert_btn)
    void ButtonClick(){
        phones = phone.getText().toString().trim();
        names = name.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String code = obj.getString("code");
                            if(code.equals("1")){
                                Intent i = new Intent(UserInsertActivity.this,UserControlActivity.class);
                                startActivity(i);
                            }
                        }
                        catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserInsertActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_PHONE,phones);
                map.put(KEY_NAME,names);
                map.put(KEY_admin,""+loginmodel.no);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
