package com.haoran.arouterpro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haoran.arouterpro.R;
import com.haoran.arouterpro.testinject.TestObj;
import com.haoran.arouterpro.testinject.TestParcelable;
import com.haoran.arouterpro.testinject.TestSerializable;

import java.util.List;
import java.util.Map;

@Route(path = "/test/activity_jnject", name = "测试用 Activity")
public class ActivityJnject extends AppCompatActivity {
    @Autowired(desc = "姓名")
    String name = "jack";

    @Autowired
    int age = 10;

    @Autowired
    int height = 175;

    @Autowired(name = "boy", required = true)
    boolean girl;

    @Autowired
    char ch = 'A';

    @Autowired
    float fl = 12.00f;

    @Autowired
    double dou = 12.01d;

    @Autowired
    TestSerializable ser;

    @Autowired
    TestParcelable pac;

    @Autowired
    TestObj obj;

    @Autowired
    List<TestObj> objList;

    @Autowired
    Map<String, List<TestObj>> map;

    private long high;

    @Autowired
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnject);
        ARouter.getInstance().inject(this);

        String params = String.format(
                "name=%s,\n age=%s, \n height=%s,\n girl=%s,\n high=%s,\n url=%s,\n ser=%s,\n pac=%s,\n obj=%s \n ch=%s \n fl = %s, \n dou = %s, \n objList=%s, \n map=%s",
                name,
                age,
                height,
                girl,
                high,
                url,
                ser,
                pac,
                obj,
                ch,
                fl,
                dou,
                objList,
                map
        );

        ((TextView) findViewById(R.id.test)).setText("I am " + ActivityJnject.class.getName());
        ((TextView) findViewById(R.id.test2)).setText(params);

    }
}