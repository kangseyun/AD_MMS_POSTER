package com.ad.jspiner.admmspost.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ad.jspiner.admmspost.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserControlActivity extends AppCompatActivity {
    @Bind(R.id.user_control_btn) Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);

        init();
    }
    void init(){
        ButterKnife.bind(this);
    }

}
