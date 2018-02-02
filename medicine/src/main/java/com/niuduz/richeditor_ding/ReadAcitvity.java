package com.niuduz.richeditor_ding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.niuduz.richeditor_ding.richeditor.RichEditor;

import gbpassenger.ichinait.com.medicine.R;

/**
 * Created by DawnOct on 2018/2/2.
 */

public class ReadAcitvity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_html);
        String html = getIntent().getStringExtra("html");
        WebView webView = (WebView) findViewById(R.id.wb);
        //直接用webView来展示,html, 含有图片
        // http://blog.csdn.net/salute_li/article/details/51992958
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }
}
