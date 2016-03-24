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
import android.widget.ListView;
import android.widget.Toast;

import com.ad.jspiner.admmspost.Adapter.MenuAdapter;
import com.ad.jspiner.admmspost.Models.LoginModel;
import com.ad.jspiner.admmspost.Models.MenuModel;
import com.ad.jspiner.admmspost.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserControlActivity extends AppCompatActivity {
    public static final String TAG = UserInsertActivity.class.getSimpleName();
    public static final String API_URL = "http://qwebmomo.cafe24.com/api/load_userlist.php?admin_no=1&page=1";

    @Bind(R.id.user_control_btn) Button btn;
    public ListView list;
    private MenuAdapter mAdapter = null;

    public LoginModel loginmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);

        init();
        load();
    }
    void init(){
        ButterKnife.bind(this);
        list = (ListView)findViewById(R.id.userList);
        mAdapter = new MenuAdapter(getApplicationContext());
        list.setAdapter(mAdapter);



    }
    void load(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray result = new JSONObject(response).getJSONArray("userlist");
                            for(int i=0; i<result.length();i++){
                                JSONObject obj = result.getJSONObject(i);
                                mAdapter.Additem(new MenuModel(obj.getString("no"), obj.getString("name"), obj.getString("signdate"), obj.getString("id"),  obj.getString("is_active")));
                                Log.i(TAG, obj.toString());
                            }

                        }
                        catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserControlActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @OnClick(R.id.user_control_btn)
    void user_control_btn(){
        Intent i = new Intent(UserControlActivity.this, UserInsertActivity.class);
        startActivity(i);
    }

}
