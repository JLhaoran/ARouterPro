package com.haoran.arouterpro;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

@Route(path = "/xx/xx")
public class DegradeServiceImpl implements DegradeService{
    Context mContext;
    @Override
    public void onLost(Context context, Postcard postcard) {

    }

    @Override
    public void init(Context context) {
        this.mContext = context ;
    }
}
