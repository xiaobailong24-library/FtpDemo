package com.xiaobailong24.ftpdemo;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 2013年09月20日21:28:51 测试demo
 *
 * 1.文件上传到FTP服务器 2.从FTP服务器上下载文件
 *
 * 所需jar包：commons-net-3.0.1.jar 将commons-net-3.0.1.jar放于libs中
 *
 * @author xiaobailong24
 *
 */
public class MainActivity extends Activity implements OnClickListener {

    private static String TAG = "MainActivity";
    // 傻逼Buttons
    private Button buttonUpLoad = null;
    private Button buttonDownLoad = null;

    // FTP工具类
    private FTPUtils ftpUtils = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取控件对象
        buttonUpLoad = (Button) findViewById(R.id.button_upload);
        buttonDownLoad = (Button) findViewById(R.id.button_download);

        // 设置控件对应相应函数
        buttonUpLoad.setOnClickListener(this);
        buttonDownLoad.setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // 初始化和FTP服务器交互的类
        InitFTPServerSetting();
    }

    public void InitFTPServerSetting() {
        // TODO Auto-generated method stub
        ftpUtils = FTPUtils.getInstance();
        boolean flag = ftpUtils.initFTPSetting("192.168.1.115", 21, "ftp",
                "000");
        if (flag) {
            Log.e(TAG, "初始化成功");
        } else {
            Log.e(TAG, "初始化失败");
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button_upload: {
                // 上传文件
                Log.e("onClick", "button_upload");
                ftpUtils.uploadFile("/mnt/sdcard/ftptest.txt", "ftptest.txt");

            }
            break;
            case R.id.button_download: {
                // 下载文件
                Log.e("onClick", "button_download");
                ftpUtils.downLoadFile("/mnt/sdcard/ftp/11.mp3", "11.mp3");

            }
            break;
            default:
                break;

        }
    }
}