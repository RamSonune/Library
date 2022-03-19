package com.example.android.mylibaray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class Books extends AppCompatActivity implements DownloadFile.Listener {

    private RemotePDFViewPager remotePDFViewPager;

    private PDFPagerAdapter pdfPagerAdapter;

    private String url;

    private ProgressBar progressBar;

    private LinearLayout pdfLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = this.getIntent();
        if (intent!=null){

            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);

            pdfLayout = findViewById(R.id.pdf_layout);

            // getting the url from the senders intent
            url = intent.getStringExtra("UrlView");

            //Create a RemotePDFViewPager object
            Toast.makeText(this, "Loading..", Toast.LENGTH_SHORT).show();
            remotePDFViewPager = new RemotePDFViewPager(this, url, this);

        }

    }

    @Override
    public void onSuccess(String url, String destinationPath) {

        // That's the positive case. PDF Download went fine
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Swipe right to read the Book", Toast.LENGTH_SHORT).show();

    }

    private void updateLayout() {

        pdfLayout.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
    }
}