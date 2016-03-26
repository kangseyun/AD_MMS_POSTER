package com.ad.jspiner.admmspost.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ad.jspiner.admmspost.Adapter.MenuAdapter;
import com.ad.jspiner.admmspost.Models.LoginModel;
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

public class UserControlActivity extends AppCompatActivity {
    public int nowPage = 1;
    public static final String TAG = UserInsertActivity.class.getSimpleName();
    public static LoginModel loginmodel;


    public static final String API_URL2 = "http://qwebmomo.cafe24.com/api/set_userable.php";

    public static String no= null;
    @Bind(R.id.user_control_btn) Button btn;
    public ListView list;
    private MenuAdapter mAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);
        Log.i("no",""+loginmodel.no);
        init();
        load(nowPage);
    }
    void init(){
        ButterKnife.bind(this);
        list = (ListView)findViewById(R.id.userList);
        mAdapter = new MenuAdapter(getApplicationContext());
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(itemClickListener);


    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            no = ((TextView) view.findViewById(R.id.listview_user_number)).getText().toString();
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(UserControlActivity.this);
            alert_confirm.setMessage("유저를 비활성화 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAdapter.dataclear();
                            load2();
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
    void load(int page){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,  String.format( "http://qwebmomo.cafe24.com/api/load_userlist.php?admin_no=%d&page=%d", loginmodel.no, page),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray result = new JSONObject(response).getJSONArray("userlist");
                            for(int i=0; i<result.length();i++){
                                JSONObject obj = result.getJSONObject(i);
                                Log.i(TAG, obj.toString());
                                mAdapter.Additem(new MenuModel(obj.getString("no"), obj.getString("name"), obj.getString("signdate"), obj.getString("id"), obj.getString("is_active")));
                                mAdapter.notifyDataSetChanged();
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

    void load2(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            load(nowPage);
                            Log.i(TAG,response);
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("user_no",no);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @OnClick(R.id.user_control_btn)
    void user_control_btn(){
        Intent i = new Intent(UserControlActivity.this, UserInsertActivity.class);
        startActivity(i);
    }
    @OnClick(R.id.user_control_btn_load)
    void user_control_btn_load(){
        nowPage= nowPage + 1;
        load(nowPage);
    }

}
