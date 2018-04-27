package fina.com.android_js;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

/*
* Android调用JS
*
* */
public class JStoAndroid extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jstoandroid);
        final WebView webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/javascript.html");
        WebSettings webSettings = webView.getSettings();
//        开启权限
        webSettings.setJavaScriptEnabled(true);
//        弹窗检验
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient() {
            //            实现自定义的弹窗
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(JStoAndroid.this);
                alertBuilder.setTitle("测试弹窗");
                alertBuilder.setMessage(message);
                alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                        Log.d("onClick", "onJSAlert");
                    }
                });
                alertBuilder.setCancelable(true);
                alertBuilder.create().show();
                return true;
            }
        });
        final Button alert = findViewById(R.id.openAlert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
//                        调用JS的方法名要对应上
//                        方法一：调用javascript的callJS()方法
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                                //                                先触发JS按钮事件再在onReceiveValue接收回调值
                                @Override
                                public void onReceiveValue(String value) {

                                }
                            });
                        } else {
                            webView.loadUrl("javascript:callJS()");
                            Log.d("webView", "loadUrl()");
                        }
                    }
                });
            }
        });
    }

}
