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

public class Money extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        ListView MoneyList = findViewById(R.id.MoneyList);

        String [] bookNameMoney = {"The Richest Man In Babylon","The Psychology of Money","Rich Dad and Poor Dad","Think and Grow Rich","Learn to Earn","Shoe Dog","4 Hour per Week"};
        String [] authorMoney = {"George Clason","Morgan Housel","Robert Kiyosaki","Napolean Hill","Peter Lynch","Phil Knight","Tim Ferris"};
        int [] imagesMoney = {R.drawable.richestbabylon,R.drawable.psychomoney,R.drawable.richdpoord,R.drawable.thinkandgrow,R.drawable.learntolearn,R.drawable.shoedog,R.drawable.fourhour};
        String [] urlMoney ={"https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/RichestManInBabylon.pdf?alt=media&token=ca0efe6b-5c96-464a-923b-3b8cf6650637",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/PsychologyOfMoney.pdf?alt=media&token=81ae4ca8-e727-48cf-9266-6c705a1e35f0",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Rich%20Dad%20Poor%20Dad.pdf?alt=media&token=e6942a35-f3a1-469a-ba13-1fe64736621c",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Think%20And%20Grow%20Rich.pdf?alt=media&token=b84f7fb5-08c2-4908-bbe8-ba6d7bc9ba8c",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/learn%20to%20earn.pdf?alt=media&token=c52c3269-7be5-43ee-8cb0-324e1cdf13f9",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Shoe%20Dog.pdf?alt=media&token=3463b72b-f598-4192-a963-452a6ac336e9",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%204-Hour%20Workweek.pdf?alt=media&token=58bc586e-1653-42b1-a38c-1779ef117d4e"};

        String [] descriptionMoney = {getResources().getString(R.string.richestbabylon),

                getResources().getString(R.string.psymoney),
                getResources().getString(R.string.richdad),
                getResources().getString(R.string.thinkandgrow),

                getResources().getString(R.string.learntoearn),
                getResources().getString(R.string.shoedog),
                getResources().getString(R.string.fourhour)};

        ArrayList<BookCustom> bookCustomCyberList = new ArrayList<>();

        for (int i =0;i <bookNameMoney.length ;i++){
            BookCustom bookCustomCyber = new BookCustom(bookNameMoney[i],authorMoney[i],imagesMoney[i],urlMoney[i],descriptionMoney[i]);
            bookCustomCyberList.add(bookCustomCyber);
        }

        BookAdapter adapterCyber = new BookAdapter(this,0,bookCustomCyberList);
        MoneyList.setAdapter(adapterCyber);

        MoneyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if the internet is off then the dialog box will appear and if the internet is on then the app will run smoothly .. makkhan
                if (!isOnline()) {
//                    Toast.makeText(ProgrammingBooks.this, "turn on internet connection", Toast.LENGTH_SHORT).show();
                    Dialog dialog = new Dialog(Money.this, R.style.NoInternetDialog);
                    dialog.setContentView(R.layout.internet_dialog);
                    dialog.show();
                    TextView retry = dialog.findViewById(R.id.retry);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Money.this, "Turn on Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Intent moneyInt = new Intent(Money.this, BookSingle.class);
                    moneyInt.putExtra("BookName", bookNameMoney[position]);
                    moneyInt.putExtra("Author", authorMoney[position]);
                    moneyInt.putExtra("ImageBook", imagesMoney[position]);
                    moneyInt.putExtra("Url", urlMoney[position]);
                    moneyInt.putExtra("Description", descriptionMoney[position]);
                    startActivity(moneyInt);
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

// was thinking of adding the steve jobs , the alchemist , nmap books will see whether to add or not and the one with the common sense


