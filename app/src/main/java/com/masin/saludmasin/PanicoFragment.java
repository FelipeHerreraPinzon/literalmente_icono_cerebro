package com.masin.saludmasin;

import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import android.widget.ImageView;


public class PanicoFragment extends Fragment {

    WebView webview;
    private ImageView loadingImageView;



    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_panico, container, false);

        webview = view.findViewById(R.id.panicoID);
        loadingImageView = view.findViewById(R.id.loadingImageView);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setGeolocationEnabled(true);



        webview.loadUrl("file:///android_asset/index.html");


        return view;
    }



}

