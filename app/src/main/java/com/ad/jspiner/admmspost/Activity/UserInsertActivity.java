package com.ad.jspiner.admmspost.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ad.jspiner.admmspost.MainActivity;
import com.ad.jspiner.admmspost.R;

import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserInsertActivity extends AppCompatActivity {
    @Bind(R.id.user_insert_phone) EditText phone;
    @Bind(R.id.user_insert_name) EditText name;
    @Bind(R.id.user_insert_btn) Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_insert);

        init();
    }

    void init(){
        ButterKnife.bind(this);
    }

}
