package com.haoran.arouterpro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haoran.arouterpro.MainActivity;
import com.haoran.arouterpro.R;
public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText text1=(EditText) findViewById(R.id.text1);
        Button finish=(Button) findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG,">>>>---ActivitySecond--");
                Intent i=new Intent();
                i.putExtra("bian", text1.getText().toString());
                setResult(4,i);
                finish();
            }
        });
    }
}