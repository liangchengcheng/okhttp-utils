package mddemo.library.com.activityanimation_master;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.Request;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mddemo.library.com.okhttp.callback.ResultCallback;
import mddemo.library.com.okhttp.request.OkHttpRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public abstract class MyResultCallback<T> extends ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            setTitle("loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            setTitle("Sample-okHttp");
        }
    }


    private ResultCallback<String> stringResultCallback = new MyResultCallback<String>(){
        @Override
        public void onError(Request request, Exception e) {
            Log.e("TAG", "onError , e = " + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            Log.e("TAG", "onResponse , response = " + response);
        }

        @Override public void inProgress(float progress) {
           //progress为当前下载的进度
        }
    };

    /**
     * 下载一个文件
     * @param view
     */
    public void downloadFile(View view) {
        String url = "url";
        new OkHttpRequest.Builder()
                .url(url)
                .destFileDir(Environment.getExternalStorageDirectory().getAbsolutePath())
                .destFileName("gson-2.2.1.jar")
                .download(stringResultCallback);
    }

    public void getUser(View view) {

        String url = "url";
        new OkHttpRequest.Builder()
                .url(url)
                .get(new MyResultCallback<User>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("TAG", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(User response) {
                        Log.e("TAG", "onResponse , user = " + response.username);
                    }
                });

    }

    public void getUsers(View view) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "zhy");
        String url = "url";
        new OkHttpRequest.Builder().url(url).params(params).post(new MyResultCallback<List<User>>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("TAG", "onError , e = " + e.getMessage());
            }

            @Override
            public void onResponse(List<User> users) {
                Log.e("TAG", "onResponse , users = " + users);
                //mTv.setText(users.get(0).toString());
            }
        });

    }

    public void getSimpleString(View view) {
        String url = "url";
        new OkHttpRequest.Builder().url(url)
                .get(new MyResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("TAG", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        // mTv.setText(response);
                    }
                });

    }

    public void getHtml(View view) {
        String url = "http://www.csdn.net/";
        new OkHttpRequest.Builder().url(url).get(new MyResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("TAG", "onError" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                //  mTv.setText(response);
            }
        });
    }

    public void getHttpsHtml(View view) {
        String url = "url";
        new OkHttpRequest.Builder().url(url).get(new MyResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("TAG", "onError" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                //mTv.setText(response);
            }
        });
    }

    public void getImage(View view) {
        String url = "http://images.csdn.net/20150817/1.jpg";
        ImageView imageView=null;
        new OkHttpRequest.Builder().url(url).imageView(imageView).displayImage(null);
    }


    public void uploadFile(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "111");
        params.put("password", "123");

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        String url = "http://192.168.56.1:8080/okHttpServer/fileUpload";
        new OkHttpRequest.Builder()//
                .url(url)//
                .params(params)
                .headers(headers)
                .files(new Pair<String, File>("mFile", file))//
                .upload(stringResultCallback);
    }


    private void postText(){
        Map<String, String> params = new HashMap<>();
        params.put("username", "111");
        params.put("password", "123");

        new OkHttpRequest.Builder().url("").params(params).post(new MyResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object resonse) {

            }
        });
    }
    public void multiFileUpload(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists()) {
            Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        String url = "http://192.168.1.103:8080/okHttpServer/mulFileUpload";
        new OkHttpRequest.Builder()
                .url(url)
                .params(params)
                .files(new Pair<String, File>("mFile", file), new Pair<String, File>("mFile", file2))//
                .upload(stringResultCallback);

    }

}
