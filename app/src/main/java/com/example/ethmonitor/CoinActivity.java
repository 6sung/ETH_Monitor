package com.example.ethmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class CoinActivity extends AppCompatActivity {
    WebView mWebView;
    WebSettings mWebSettings;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        Intent intent = getIntent();
        String website = intent.getExtras().getString("website");
        ImageButton button1 = (ImageButton) findViewById(R.id.goHomeButton3) ;
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoinActivity.this, MainActivity.class);
                startActivity(intent);
                // TODO : click event
            }
        });

        mWebView = (WebView) findViewById(R.id.mWebview);
        mWebSettings = mWebView.getSettings();
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setCacheMode(mWebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);

        if(website.contains("gopax")) mWebView.loadUrl("https://www.gopax.co.kr/exchange/eth-btc");
        else if(website.contains("bithumb")) mWebView.loadUrl("https://www.bithumb.com/trade/order/ETH_KRW");
        else if(website.contains("upbit")) mWebView.loadUrl("https://www.upbit.com/exchange?code=CRIX.UPBIT.KRW-ETH");
    }
    private class WebViewClientClass extends WebViewClient {
        // SSL 인증서 무시
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        // 페이지 내에서만 url 이동하게끔 만듬
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}