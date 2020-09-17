package com.haoran.arouterpro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haoran.arouterpro.MainActivity;
import com.haoran.arouterpro.R;

public class ActivityFirst extends AppCompatActivity {

    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textview=(TextView) findViewById(R.id.textview);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ActivityFirst.this,
                        ActivitySecond.class), 1);
            }
        });

        findViewById(R.id.bt_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.d(MainActivity.TAG,">>>>-ActivityFirst-退出--");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(MainActivity.TAG,">>>>-ActivityFirst---requestCode="+requestCode+"--resultCode="+resultCode);
        if (requestCode == 1 && resultCode == 4) {
            Log.d(MainActivity.TAG,">>>>-ActivityFirst---");
            String s=data.getStringExtra("bian");
            textview.setText(s);
        }
    }

}