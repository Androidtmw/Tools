package com.tmw.tools.util;

import android.app.Application;

/**
 * Created by tmw on 2016/2/16.
 */
public class AppContext extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ShareUtil.initShared(this);
    }
}
