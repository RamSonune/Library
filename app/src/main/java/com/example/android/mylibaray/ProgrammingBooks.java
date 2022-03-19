package com.example.android.mylibaray;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProgrammingBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_books);

        ListView listViewProgramming  = findViewById(R.id.listViewProgramming);

        String [] nameOfProgBooks = {"Python For Beginners","Head First Android Development","Learn Programming With C",
                "Learn JavaScript With Ease","Data Analysis From Scratch with Python","C++ Programming For Beginners",
        "Machine Learning"};
        String [] authorProgBooks = {"Adam Stewarts","Dawn Griffiths and David Griffiths","Noel Kalicharan","Stephen Bluementhal","Peters Morgan","Malini Devi","Rudolf Russel"};
        int [] imageResProg = {R.drawable.pylogo,R.drawable.android,R.drawable.c,R.drawable.javascript,R.drawable.datascience,R.drawable.cpp,R.drawable.machine};

        String [] urlProg ={"https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Python%20Programming.pdf?alt=media&token=0e5024c1-94ef-4cf6-9b31-fdcc0262465c",
        "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts/en/android-developer-fundamentals-course-concepts-en.pdf",
        "https://www.cplusplus.com/files/tutorial.pdf",

        "https://www.cplusplus.com/files/tutorial.pdf",
        "https://www.cplusplus.com/files/tutorial.pdf",
        "https://www.cplusplus.com/files/tutorial.pdf",
        "https://www.cplusplus.com/files/tutorial.pdf"};

        String well = getString(R.string.android);

        String [] description={getResources().getString(R.string.python),getString(R.string.android),
        getResources().getString(R.string.cprog),

        getResources().getString(R.string.javascript),
        getResources().getString(R.string.datasci),
        getResources().getString(R.string.android),

        getResources().getString(R.string.machinelearn)};


        ArrayList<BookCustom> bookCustomArrayList = new ArrayList<>();

        for (int i =0 ; i<nameOfProgBooks.length ;i++){
            BookCustom bookCustomProg = new BookCustom(nameOfProgBooks[i],authorProgBooks[i],imageResProg[i],urlProg[i],description[i]);
            bookCustomArrayList.add(bookCustomProg);
        }

        BookAdapter bookAdapter = new BookAdapter(this,0,bookCustomArrayList);

        listViewProgramming.setAdapter(bookAdapter);

        listViewProgramming.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isOnline()){
//                    Toast.makeText(ProgrammingBooks.this, "turn on internet connection", Toast.LENGTH_SHORT).show();
                    Dialog dialog = new Dialog(ProgrammingBooks.this,R.style.NoInternetDialog);
                    dialog.setContentView(R.layout.internet_dialog);
                    dialog.show();
                    TextView retry = dialog.findViewById(R.id.retry);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ProgrammingBooks.this, "Turn on Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    Intent progInt = new Intent(ProgrammingBooks.this,BookSingle.class);
                    // the below one should be common for every book intent , since the receiver class is the single class only
                    progInt.putExtra("BookName",nameOfProgBooks[position]);
                    progInt.putExtra("Author",authorProgBooks[position]);
                    progInt.putExtra("ImageBook",imageResProg[position]);
                    progInt.putExtra("Description",description[position]);
                    // add the url too .
                    progInt.putExtra("Url",urlProg[position]);
                    startActivity(progInt);

                }

            }
        });





    }

    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()){
            return false ;
        }
        return true;
    }
}
/*
notebook , intro to algorithm , thousand stitches , little book of common sense investing
* */