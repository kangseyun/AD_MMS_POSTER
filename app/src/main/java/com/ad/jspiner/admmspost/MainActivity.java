package com.ad.jspiner.admmspost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



import com.ad.jspiner.admmspost.Activity.LoginActivity;


public class MainActivity extends Activity {
    private Handler mHandler;
    private Runnable mRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext()
                        ,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2000);
    }
    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
