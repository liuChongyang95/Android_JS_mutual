# Android_JS_mutual
一、Android向JS的交互
WebView loadUrl()/evaluateJavascript()

1. 先将JS以.html格式放到src/main/assets文件夹里
(实际是调用JS代码，可能需要绝对路径URL)

webView 载体
WebSettings 加载WebView,设置状态
webviewChromeClient实现webView内容渲染
new webviewChromeClient中onJsAlert()弹出内容 result.confirm();

1.loadUrl方式先加载javascript：方法名，再去取回调值////////不好取值的方式
2.evaluatJavascript("javascript：方法名"，new ValueCallBack "onReceiveValue")//简写/////效率高，兼容性差

二、JS通过WebView调用Android代码
1.WebView的addJavaScriptinterface()进行对象映射
2.webviewclient的shouldOverrideUrlLoading()方法拦截callbackUrl()
3.webChromeClient的onJsAlert()、onJsConfirm()、onJsPrompt()方法回调拦截JS对话框alert()，confirm(),prompt()消息

WebView addJavascriptInterface()进行对象映射
Button没有value设定 用Input
注意：@JavascriptInterface加在调用方法上面

通过webviewClient的shouldoverrideurlloading()回调拦截url
1.通过shouldOverrideUrlLoading()拦截url
2.解析url
3.检测是否预先约定好

通过WebChromeClient的onJsAlert()/onJsConfirm()/onJsPrompt()方法回调
拦截JS对话框alert()/confirm()/prompt()消息
prompt可以返回任意类型的值
alert没有返回值
confirm只能返回两种状态

回调中调用prompt就调用onJsPrompt().其他同理