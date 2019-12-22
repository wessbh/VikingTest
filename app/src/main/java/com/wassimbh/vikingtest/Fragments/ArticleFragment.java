package com.wassimbh.vikingtest.Fragments;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.wassimbh.vikingtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private WebView mWebview ;
    private String external_link;
    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            external_link = bundle.getString("external_link", "https://google.com");
        }
        mWebview = (WebView) view.findViewById(R.id.webview);
        mWebview.loadUrl(external_link);

        // Enable Javascript
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebview.setWebViewClient(new WebViewClient());

        return view;
    }

}
