package com.ad.jspiner.admmspost.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
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

public class AdminControlActivity extends Activity implements AbsListView.OnScrollListener {
    public boolean rock = true;
    public int nowPage = 1;
    public int check = 0;
    public static final String TAG = AdminControlActivity.class.getSimpleName();
    public static final String API_URL2 = "http://qwebmomo.cafe24.com/api/set_adminable.php";

    public LoginModel loginmodel;
    public static String no;

    private MenuAdapter mAdapter = null;

    @Bind(R.id.adminList)
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);
        init();
        road(1);
    }

    void init() {
        ButterKnife.bind(this);
        mAdapter = new MenuAdapter(getApplicationContext());
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(itemClickListener);

        list.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (rock == false) {
            if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                rock = true;
                road(nowPage);
            }
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            no = ((TextView) view.findViewById(R.id.listview_user_number)).getText().toString();
            Log.i("get", no);
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(AdminControlActivity.this);
            alert_confirm.setMessage("유저를 활서화/비활성화 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAdapter.dataclear();
                            load2();
                            // 리스트 클리어 - > 업데이트 리스트 목록 불러오기 -> 리스트 리플레쉬
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

    void road(int page) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format("http://qwebmomo.cafe24.com/api/load_adminlist.php?page=%d", page),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            nowPage = nowPage + 1;
                            JSONObject totalpage = new JSONObject(response);
                            int Count = totalpage.getInt("lastpage");
                            if (nowPage < Count) {

                                Log.i("nowPage", "" + nowPage);
                                JSONArray result = new JSONObject(response).getJSONArray("adminlist");
                                int len = result.length();

                                for (int i = 0; i < len; i++) {
                                    JSONObject obj = result.getJSONObject(i);
                                    mAdapter.Additem(new MenuModel(obj.getString("no"), obj.getString("name"), obj.getString("signdate"), obj.getString("id"), obj.getString("is_active")));


                                }
                                mAdapter.notifyDataSetChanged();
                                rock = false;
                            }


                        } catch (Exception e) {
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminControlActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    void load2() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            nowPage = 1;
                            road(nowPage);
                        } catch (Exception e) {
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminControlActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("admin_no", no);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @OnClick(R.id.admin_control_btn)
    void admin_control_btn() {
        Intent i = new Intent(AdminControlActivity.this, AdminInsertActivity.class);
        startActivity(i);
        finish();
    }


}
