package com.ad.jspiner.admmspost.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends Activity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String API_URL = "http://qwebmomo.cafe24.com/api/login_admin.php";

    public static final String KEY_USERNAME = "id";
    public static final String KEY_PASSWORD = "pw";

    public LoginModel loginmodel;
    private String username;
    private String password;

    @Bind(R.id.ButtonLogin)
    Button login;
    @Bind(R.id.loginid)
    EditText id;
    @Bind(R.id.loginpassword)
    EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    void init() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ButtonLogin)
    void ButtonClick() {
        username = id.getText().toString().trim();
        password = pw.getText().toString().trim();
        // Login Request
        // 이거 클래스화 시켜야하는데 일단 이렇게 해놓고 나중에 리팩토링할때 클래스 화 할게 그럼 코드 깔끔해질거야

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);

                            loginmodel.logined = result.getInt("code");
                            loginmodel.is_master = result.getInt("is_master");
                            loginmodel.no = result.getInt("no");
                            Log.i(TAG, "" + loginmodel.no);

                        } catch (Exception e) {
                        }

                        if (loginmodel.logined == 1) {
                            loginComplete();
                        } else {
                            Toast.makeText(LoginActivity.this, "LOGIN FAIL", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void loginComplete() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }


}
