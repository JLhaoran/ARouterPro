package com.haoran.arouterpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haoran.arouterpro.activity.ActivityFirst;
import com.haoran.arouterpro.testinject.TestObj;
import com.haoran.arouterpro.testinject.TestParcelable;
import com.haoran.arouterpro.testinject.TestSerializable;
import com.haoran.arouterpro.testservice.HelloService;
import com.haoran.arouterpro.testservice.SingleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   public static final String TAG = "zg";
    private static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(MainActivity.TAG,">>>>----requestCode="+requestCode+"--resultCode="+resultCode);
        if (requestCode == 2 && resultCode == 6) {
            String s=data.getStringExtra("key");
            Log.d(MainActivity.TAG,">>>>-ActivityFirst---s="+s);
        }
    }

    public static Activity getThis() {
        return activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openLog:
                ARouter.openLog();
                break;
            case R.id.openDebug:
                ARouter.openDebug();
                break;
            case R.id.init:
                // 初始化
                // 调试模式不是必须开启，但是为了防止有用户开启了InstantRun，但是
                // 忘了开调试模式，导致无法使用Demo，如果使用了InstantRun，必须在
                // 初始化之前开启调试模式，但是上线前需要关闭，InstantRun仅用于开
                // 发阶段，线上开启调试模式有安全风险，可以使用BuildConfig.DEBUG
                // 来区分环境
                ARouter.openDebug();
                ARouter.init(getApplication());
                break;
            case R.id.normalNavigation://简单的应用内跳转
                ARouter.getInstance()
                        .build("/test/activity1")
                        .navigation();
                break;
            case R.id.normalNavigationWithParams:
                 ARouter.getInstance()
                         .build("/test/activity2")
                         .withString("key1", "value1")
                         .navigation();
                break;
            case R.id.oldVersionAnim: //注：旧版本版本动画
                ARouter.getInstance()
                        .build("/test/activity2")
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation(this);
                break;
            case R.id.newVersionAnim:  //注：新版本版本动画
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                    ARouter.getInstance()
                            .build("/test/activity2")
                            .withOptionsCompat(compat)
                            .navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.interceptor: //拦截器测试
                ARouter.getInstance()
                        .build("/test/activity4")
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                            }
                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.d("ARouter", "被拦截了");
                            }
                        });
                break;
            case R.id.navByUrl:
                ARouter.getInstance()
                        .build("/test/webview")
                        .withString("url", "file:///android_asset/scheme-test.html")
                        .navigation();
                break;
            case R.id.autoInject:
                Log.d(TAG,">>>>---依赖注入测试--<<<--");
                TestSerializable testSerializable = new TestSerializable("Titanic", 555);
                TestParcelable testParcelable = new TestParcelable("jack", 666);
                TestObj testObj = new TestObj("Rose", 777);
                List<TestObj> objList = new ArrayList<>();
                objList.add(testObj);
                Map<String, List<TestObj>> map = new HashMap<>();
                map.put("testMap", objList);
                ARouter.getInstance().build("/test/activity_jnject")
//                ARouter.getInstance().build("/test/activity1")
                        .withString("name", "老王")
                        .withInt("age", 18)
                        .withBoolean("boy", true)
                        .withLong("high", 180)
                        .withString("url", "https://a.b.c")
                        .withSerializable("ser", testSerializable)
                        .withParcelable("pac", testParcelable)
                        .withObject("obj", testObj)
                        .withObject("objList", objList)
                        .withObject("map", map)
                        .navigation();
                break;
            case R.id.navByName://注：调用服务ByName
                ((HelloService) ARouter.getInstance().build("/yourservicegroupname/hello").navigation()).sayHello("mike");
                break;
            case R.id.navByType://注： 调用服务ByType
                ARouter.getInstance().navigation(HelloService.class).sayHello("mike");
                break;
            case R.id.callSingle://调用单类
                ARouter.getInstance().navigation(SingleService.class).sayHello("Mike");
                break;
            case R.id.navToMoudle1://注:多模块测试，跳转到模块1
                ARouter.getInstance().build("/module/1").navigation();
                break;
            case R.id.navToMoudle2://注:多模块测试，跳转到模块2
                // 这个页面主动指定了Group名
                ARouter.getInstance().build("/module/2", "m2").navigation();
                break;
            case R.id.destroy:
                ARouter.getInstance().destroy();
                break;
            //注:跳转失败  降级策略
            case R.id.failNav:   //注: 跳转失败，单独降级
                ARouter.getInstance().build("/xxx/xxx").navigation(this, new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d("ARouter", "找到了");
                    }
                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d("ARouter", "找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d("ARouter", "跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d("ARouter", "被拦截了");
                    }
                });
                break;
            case R.id.failNav2://注: 跳转失败 全局降级
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
            case R.id.failNav3://注: 服务调用失败
                ARouter.getInstance().navigation(MainActivity.class);
                break;
            case R.id.normalNavigation2://注：跳转ForResult
                ARouter.getInstance()
                        .build("/test/activity2")
                        .navigation(this, 2);
                break;
            case R.id.normalNavigation3://注：普通跳转ForResult
                startActivityForResult(new Intent(MainActivity.this,
                        ActivityFirst.class), 1);
                break;
            case R.id.getFragment:
                Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment").navigation();
                Toast.makeText(this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


}