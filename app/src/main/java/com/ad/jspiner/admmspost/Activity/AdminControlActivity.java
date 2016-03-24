package com.ad.jspiner.admmspost.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ad.jspiner.admmspost.Adapter.MenuAdapter;
import com.ad.jspiner.admmspost.Models.MenuModel;
import com.ad.jspiner.admmspost.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminControlActivity extends Activity {
    public static final String TAG = AdminControlActivity.class.getSimpleName();
    public static final String API_URL = "http://qwebmomo.cafe24.com/api/load_adminlist.php?page=1";
    public static final String API_URL2 = "http://qwebmomo.cafe24.com/api/set_adminable.php";

    public static final String KEY_PHONE="user_no";


    private MenuAdapter mAdapter = null;
    @Bind(R.id.adminList) ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);
        init();
        road();
    }
    void init(){
        ButterKnife.bind(this);
        mAdapter = new MenuAdapter(getApplicationContext());
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(itemClickListener);
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(AdminControlActivity.this);
            alert_confirm.setMessage("유저를 비활성화 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'YES'
                        }
                    }).setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'No'
                            return;
                        }
                    });
            AlertDialog alert = alert_confirm.create();
            alert.show();
        }
    };
    void road(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray result = new JSONObject(response).getJSONArray("adminlist");
                            for(int i=0; i<result.length();i++){
                                JSONObject obj = result.getJSONObject(i);
                                mAdapter.Additem(new MenuModel(obj.getString("no"), obj.getString("name"), obj.getString("signdate"), obj.getString("id"), obj.getString("is_active")));
                                        Log.i(TAG, obj.toString());
                            }

                        }
                        catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminControlActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    void load2(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray result = new JSONObject(response).getJSONArray("adminlist");

                        }
                        catch (Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminControlActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @OnClick(R.id.admin_control_btn)
    void admin_control_btn(){
        Intent i = new Intent(AdminControlActivity.this, AdminInsertActivity.class);
        startActivity(i);
    }
}
