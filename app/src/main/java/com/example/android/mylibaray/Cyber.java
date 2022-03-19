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

public class Cyber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber);

        ListView cyberList = findViewById(R.id.CyberView);

        String [] bookName =  {"Computer Network","Linux : The Complete Reference","Computer Communication and Networking","Linux Basics for Hackers","CEH Study Guide","The Internet Book"};
        String [] author = {"Tanenbaum","Richard Peterson","Springer","OccupyTheWeb","Kimberley Graves","Doughlas Corner"};
        int [] imageBook =  {R.drawable.computernettanen,R.drawable.linuxcomplete,R.drawable.computernetspringer,R.drawable.linuxhackes,R.drawable.ceh11,R.drawable.theinternetbook};
        String [] urlCyber = {"https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Computer%20networks.pdf?alt=media&token=300405b5-ab22-47dd-ab4e-a4bb67059dfe",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Linux%20-%20The%20Complete%20Reference.pdf?alt=media&token=57905c23-57f4-4606-84da-c8a2c46ad72c",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Computer%20Communication%2C%20Networking.pdf?alt=media&token=d32b5ca5-2ef2-4695-bf27-a296058c8c62",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Linux%20Basics%20for%20Hackers.pdf?alt=media&token=19b1da3c-89d7-41dc-86e3-09e7ea42ca34",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/CEHKimberley.pdf?alt=media&token=63c9cac1-983e-447f-8bbc-7bcc568c9eab",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Internet%20Infrastructure.pdf?alt=media&token=25a51d34-842d-4425-a373-935ee85207a4"};

        String [] descriptionCyber = {getResources().getString(R.string.tancn),
        getResources().getString(R.string.linuxcomplete),
        getResources().getString(R.string.cnetspringer),

        getResources().getString(R.string.linuxkali),
        getResources().getString(R.string.ceh),
        getResources().getString(R.string.internetbook)};

        ArrayList<BookCustom> arrayListCyber = new ArrayList<>();

        for (int i =0; i<bookName.length ;i++){
            BookCustom bookCustomCyber = new BookCustom(bookName[i],author[i],imageBook[i],urlCyber[i],descriptionCyber[i]);
            arrayListCyber.add(bookCustomCyber);
        }
        BookAdapter bookAdapterCyber = new BookAdapter(this,0,arrayListCyber);

        cyberList.setAdapter(bookAdapterCyber);

        cyberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if the internet is off then the dialog box will appear and if the internet is on then the app will run smoothly .. makkhan
                if (!isOnline()) {
//                    Toast.makeText(ProgrammingBooks.this, "turn on internet connection", Toast.LENGTH_SHORT).show();
                    Dialog dialog = new Dialog(Cyber.this, R.style.NoInternetDialog);
                    dialog.setContentView(R.layout.internet_dialog);
                    dialog.show();
                    TextView retry = dialog.findViewById(R.id.retry);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Cyber.this, "Turn on Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    Intent cyberInt = new Intent(Cyber.this, BookSingle.class);
                cyberInt.putExtra("BookName", bookName[position]);
                cyberInt.putExtra("Author", author[position]);
                cyberInt.putExtra("ImageBook", imageBook[position]);
                cyberInt.putExtra("Url", urlCyber[position]);
                // add the description too :
                cyberInt.putExtra("Description", descriptionCyber[position]);
                startActivity(cyberInt);
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
//progInt.putExtra("BookName",nameOfProgBooks[position]);
//        progInt.putExtra("Author",authorProgBooks[position]);
//        progInt.putExtra("ImageBook",imageResProg[position]);
//        progInt.putExtra("Description",description[position]);
//        // add the url too .
//        progInt.putExtra("Url",urlProg[position]);
// add the book of the nmap and the think like the programmer . also if possible then wireshark .