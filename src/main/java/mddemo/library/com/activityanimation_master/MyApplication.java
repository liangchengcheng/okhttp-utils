package mddemo.library.com.activityanimation_master;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import mddemo.library.com.okhttp.manager.OkHttpClientManager;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月26日16:04:10
 * Description:
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClientManager.getInstance().getOkHttpClient().setConnectTimeout(100000, TimeUnit.MILLISECONDS);
    }
}
