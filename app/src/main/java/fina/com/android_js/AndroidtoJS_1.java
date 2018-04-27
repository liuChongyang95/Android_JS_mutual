package fina.com.android_js;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.util.HashMap;
import java.util.Set;

/*
 * JS中调用Android
 * */
public class AndroidtoJS_1 extends AppCompatActivity {

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    @JavascriptInterface
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.androidtojs);
        final WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        android类AndroidtoJS_2()映射到JS的test上
//        webView.addJavascriptInterface(new AndroidtoJS_2(this), "test");

        webView.loadUrl("file:///android_asset/javascript.html");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri=Uri.parse(url);
                if (uri.getScheme().equals("js")){
                    if (uri.getAuthority().equals("webview")){
                        System.out.println("JS调用了Android");
                        HashMap<String,String> params=new HashMap<>();
                        Set<String> collection=uri.getQueryParameterNames();
                        //    发送返回
//                        webView.loadUrl("javascript:returnResult("+collection[1]+")");

                    }
                    return true;
                }
               return super.shouldOverrideUrlLoading(view,url);
            }
        });
        //    发送返回值
//        webView.loadUrl("javascript:returnResult("+result+")");
    }
}
