package com.masin.saludmasin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


public class PortalesFragment extends Fragment {

    WebView webview;
    private ImageView loadingImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portales, container, false);

        webview = view.findViewById(R.id.portalesID);
        loadingImageView = view.findViewById(R.id.loadingImageView);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);


        if (isNetworkAvailable()) {
            webview.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    // Mostrar imagen de carga

                    loadingImageView.setVisibility(View.VISIBLE);

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    // Ocultar imagen de carga

                    loadingImageView.setVisibility(View.GONE);

                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith("mailto:")) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    } else if(url.startsWith("tel:")) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }


                    else {

                        view.loadUrl(url);
                        return true;
                    }


                }

            });
            webview.loadUrl("https://www.saludmasin.com/salud-virtu\n" +
                    "al-masin");
        } else {
            loadingImageView.setVisibility(View.GONE);
            webview.loadUrl("file:///android_asset/no_internet.html");
        }

        return view;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}