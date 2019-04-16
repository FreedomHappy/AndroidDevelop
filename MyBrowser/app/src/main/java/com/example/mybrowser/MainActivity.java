package com.example.mybrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String  uri = "http://www.baidu.com";
        if (intent!=null){
            String msg = intent.getStringExtra("msg");
            if (!(msg==null||msg.isEmpty())){
                uri = msg;
            }
        }
        //如果访问的页面中有 Javascript,则 WebView 必须设置支持 Javascript。
        myWebView.getSettings().setJavaScriptEnabled(true);
        // 如果页面中链接,如果希望点击链接继续在当前browser中响应,
        // 而不是新开Android的系统browser中响应该链接,必须覆盖 WebView的WebViewClient对象。
        myWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        myWebView.loadUrl(uri);
    }
}
