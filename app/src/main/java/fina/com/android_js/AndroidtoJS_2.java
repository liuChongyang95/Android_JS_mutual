package fina.com.android_js;


import android.content.Context;
import android.webkit.JavascriptInterface;

public class AndroidtoJS_2 {

    Context context;
    public AndroidtoJS_2(Context c){
        this.context=c;
    }
    @JavascriptInterface
    public void hello(String msg){
        System.out.println(msg);
    }

}
