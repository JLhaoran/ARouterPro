package com.haoran.arouterpro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haoran.arouterpro.R;

@Route(path = "/test/activity4")
public class ActivityTest4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        ((TextView)findViewById(R.id.test)).setText("I am " + ActivityTest4.class.getName());
        String extra = getIntent().getStringExtra("extra");
        if (!TextUtils.isEmpty(extra)) {
            ((TextView)findViewById(R.id.test2)).setText(extra);
        }
    }
}