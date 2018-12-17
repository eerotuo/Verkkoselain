package com.example.eerot.website;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    WebView web;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        web = findViewById(R.id.webView);
        editText = findViewById(R.id.editText);

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);

        editText.setOnEditorActionListener(editorListner);
    }

    private TextView.OnEditorActionListener editorListner = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case(EditorInfo.IME_ACTION_DONE):
                    url = editText.getText().toString();

                    if (url.equals("index.html")){
                        web.loadUrl("file:///android_asset/index.html");
                    }
                    else {
                        web.loadUrl("http://" + url);
                    }
            }

            return false;
        }
    };

    public void refreshWebURL(View v){
        web.reload();
    }

    public void shout(View v){
        web.evaluateJavascript("shoutOut()",null);
    }

    public void initialize(View v){
        web.evaluateJavascript("initialize()",null);
    }
    public void goBack(View v){
        web.goBack();
    }

    public void goForward(View v){
        web.goForward();
    }
}
