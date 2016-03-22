package com.ad.jspiner.admmspost.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ad.jspiner.admmspost.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ADSetActivity extends AppCompatActivity {
    @Bind(R.id.btn_adset_imgload) Button btn_img;
    @Bind(R.id.btn_adset_save) Button save;
    @Bind(R.id.edtext_ad_content) EditText Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adset);

        init();
    }
    void init(){
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_adset_imgload)
    void imgLoad(){

    }

    @OnClick(R.id.btn_adset_save)
    void adSave(){

    }
}
