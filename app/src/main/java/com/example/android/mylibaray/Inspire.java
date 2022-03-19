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

public class Inspire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspire);

        ListView InspireList = findViewById(R.id.InspireList);



        String [] inspireBookName = {"Wings Of Fire","The Miracle Morning","Ikigai" ,"I am Malala","The Monk Who Sold His Ferrari","Think Like A Monk","Seven Habits of Highly Effective People","12 Rules For Life","Elon Musk",
        "Deep Work","Compound Effect"};

        String [] inspireAuthor = {"Dr APJ Abdul Kalam and Arun Tiwari","Hal Elrod","Hector Garcia",  "Malala Yousafzai and Christina Lamb","Robin Sharma","Jay Shetty","Stephen Covey","Jordan Peterson","Ashlee Vance",
        "Cal Newport","Darren Hardy"};

        int [] inspireImageRes = {R.drawable.apjabdulji,R.drawable.miracle,R.drawable.ikigai,R.drawable.malala,R.drawable.monkrobin,R.drawable.thinklikemonk,R.drawable.sevenhabits,R.drawable.twelverule,R.drawable.elon
                ,R.drawable.deepwork,R.drawable.compound};

        String [] url ={"https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Wings%20of%20fire.pdf?alt=media&token=aeee422b-490c-45b0-b8f4-cdd9c750cdf2",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%20Miracle%20Morning.pdf?alt=media&token=2ba21a98-4821-4e7b-81be-1ca477f67dbb",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Ikigai.pdf?alt=media&token=432bba9b-b54a-4d43-8871-ce37c28e903a",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/I%20am%20Malala.pdf?alt=media&token=c15328f4-c598-4b95-91f6-0ce07f78f83c",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%20Monk%20Who%20Sold.pdf?alt=media&token=d70b82c5-fa5e-4c54-aeb8-cacfdf6f15ab",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/jayshetty.pdf?alt=media&token=d7f253fc-edf9-49c4-8747-251cd9644408",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/seven%20habits.pdf?alt=media&token=23efbefc-ea79-4b10-94c1-880395c8ff00",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/12-Rules-for-Life.pdf?alt=media&token=634ad618-f678-4182-8f66-de81daa64139",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Elon%20Musk.pdf?alt=media&token=acff8207-4b6d-449f-a0db-5579e3e276f3",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Deep%20Work.pdf?alt=media&token=59feaa62-dcc9-499f-8372-e58fb72f6bf1",
        "http://www.sarahnamulondo.com/wp-content/uploads/2015/11/The-Compound-Effect-By-darren-Hardy.pdf"};

        String [] descriptionInspire = {getResources().getString(R.string.wingsofire),
                getResources().getString(R.string.miraclesubah),
                getResources().getString(R.string.ikigai),

                getResources().getString(R.string.malala),
                getResources().getString(R.string.monksold),
                getResources().getString(R.string.thinkmonk),

                getResources().getString(R.string.sevenhabits),
                getResources().getString(R.string.twelverules),
                getResources().getString(R.string.elon),

                getResources().getString(R.string.deepwork),
                getResources().getString(R.string.compound)};

        ArrayList<BookCustom> bookCustomArrayListInspire = new ArrayList<>();

        for (int i =0; i<inspireBookName.length ;i++){
            BookCustom customInspire = new BookCustom(inspireBookName[i],inspireAuthor[i],inspireImageRes[i],url[i],descriptionInspire[i]);
            bookCustomArrayListInspire.add(customInspire);
        }


        BookAdapter inspireAdapter = new BookAdapter(this,0,bookCustomArrayListInspire);

        InspireList.setAdapter(inspireAdapter);

        InspireList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if the internet is off then the dialog box will appear and if the internet is on then the app will run smoothly .. makkhan
                if (!isOnline()) {
//                    Toast.makeText(ProgrammingBooks.this, "turn on internet connection", Toast.LENGTH_SHORT).show();
                    Dialog dialog = new Dialog(Inspire.this, R.style.NoInternetDialog);
                    dialog.setContentView(R.layout.internet_dialog);
                    dialog.show();
                    TextView retry = dialog.findViewById(R.id.retry);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Inspire.this, "Turn on Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Intent inspireInt = new Intent(Inspire.this, BookSingle.class);
                    inspireInt.putExtra("BookName", inspireBookName[position]);
                    inspireInt.putExtra("Author", inspireAuthor[position]);
                    inspireInt.putExtra("ImageBook", inspireImageRes[position]);
                    inspireInt.putExtra("Url", url[position]);
                    inspireInt.putExtra("Description", descriptionInspire[position]);
                    startActivity(inspireInt);
                }
            }
        });

    }

    // method to check the internet connection of the user
    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()){
            return false ;
        }
        return true;
    }
}