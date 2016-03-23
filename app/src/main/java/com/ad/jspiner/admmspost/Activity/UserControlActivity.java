package com.ad.jspiner.admmspost.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ad.jspiner.admmspost.Adapter.MenuAdapter;
import com.ad.jspiner.admmspost.Models.MenuModel;
import com.ad.jspiner.admmspost.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserControlActivity extends AppCompatActivity {
    @Bind(R.id.user_control_btn) Button btn;
    public ListView list;
    private MenuAdapter mAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_control);

        init();
    }
    void init(){
        ButterKnife.bind(this);
        list = (ListView)findViewById(R.id.userList);
        mAdapter = new MenuAdapter(getApplicationContext());
        list.setAdapter(mAdapter);
        mAdapter.Additem(new MenuModel("1", "강세윤", "2015-09-07", "010-6799-3259"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));
        mAdapter.Additem(new MenuModel("2", "박영진", "2015-09-07", "010-3362-8717"));

    }

    @OnClick(R.id.user_control_btn)
    void user_control_btn(){
        Intent i = new Intent(UserControlActivity.this, UserInsertActivity.class);
        startActivity(i);
    }

}
