package com.ad.jspiner.admmspost.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ad.jspiner.admmspost.Models.LoginModel;
import com.ad.jspiner.admmspost.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    @Bind(R.id.btn_menu_uesr)
    Button btn_user;
    @Bind(R.id.logout)
    Button logout;
    @Bind(R.id.btn_menu_admin)
    Button btn_admin;

    public static LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();

    }

    void init() {
        ButterKnife.bind(this);

        if (loginModel.is_master == 0) {
            btn_admin.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_menu_uesr)
    void UserButtonClick() {
        Intent i = new Intent(MenuActivity.this, UserControlActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_menu_admin)
    void AdminButtonClick() {
        Intent i = new Intent(MenuActivity.this, AdminControlActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.logout)
    void logout() {
        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(i);
    }

}
