package com.haoran.arouterpro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.haoran.arouterpro.MainActivity;
import com.haoran.arouterpro.R;
@Route(path = "/test/activity2")
public class ActivityTest2 extends AppCompatActivity {

//    @Autowired
//    String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        String value = getIntent().getStringExtra("key1");
        if (!TextUtils.isEmpty(value)) {
            Toast.makeText(this, "exist param :" + value, Toast.LENGTH_LONG).show();
        }


        Button finish=(Button) findViewById(R.id.quit);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG,">>>>---ActivitySecond--");
                Intent i=new Intent();
                i.putExtra("key","result");
                setResult(6,i);
                finish();
            }
        });

    }
}