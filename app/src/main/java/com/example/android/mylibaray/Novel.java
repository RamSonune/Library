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

public class Novel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);

        ListView Novels = findViewById(R.id.NovelList);

        String [] bookNameNovel = {"The Girl in Room 105","Half Girlfriend","Fault In Our Stars","The Palace of Illusions","1000 Splendid Suns","The Kite Runner"};
        String [] authorNovel = {"Chetan Bhagat","Chetan Bhagat","John Green","Chitra Lekha","Khaled Hosseini","Khaled Hosseini"};
        int [] imagesNovel = {R.drawable.roomonefive,R.drawable.halfgf,R.drawable.faultstars,R.drawable.thepalace,R.drawable.thousandrun,R.drawable.kiterunner};
        String [] urlNovel = {"https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%20girl%20in%20room%20105.pdf?alt=media&token=f7f9da46-f552-48b5-a248-b76a091c6f60",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/Half%20Girlfriend.pdf?alt=media&token=5ea64ef9-ec15-44be-a75c-6336b80b72d8",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/fault.pdf?alt=media&token=f7458785-c4cc-4420-b33f-e6f7251cfbe6",

        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%20Palace%20of%20Illusions.pdf?alt=media&token=f55269be-d01a-4de6-b161-23a7dbe9dcf1",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/A%20Thousand%20Splendid%20Suns.pdf?alt=media&token=c192cf5a-c5f9-47e7-bf0c-ea8940f19385",
        "https://firebasestorage.googleapis.com/v0/b/fir-71644.appspot.com/o/The%20Kite%20Runner.pdf?alt=media&token=bd3eab49-27cb-4918-bd7a-8c1fe8e52d9d"};

        String [] descriptionNovel = {getResources().getString(R.string.thegirlinroom),
                getResources().getString(R.string.addhipremika),
                getResources().getString(R.string.fault),

                getResources().getString(R.string.thepalace),
                getResources().getString(R.string.thousandsuns),
                getResources().getString(R.string.kiterunner)};
        // add another book of the paulo the alchemist

        ArrayList<BookCustom> arrayListNovel = new ArrayList<>();


        for (int i = 0; i<bookNameNovel.length ;i++ ){
            BookCustom bookCustomNovel = new BookCustom(bookNameNovel[i],authorNovel[i],imagesNovel[i],urlNovel[i],descriptionNovel[i]);
            arrayListNovel.add(bookCustomNovel);
        }

        BookAdapter adapterNovel = new BookAdapter(this,0,arrayListNovel);

        Novels.setAdapter(adapterNovel);

        Novels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if the internet is off then the dialog box will appear and if the internet is on then the app will run smoothly .. makkhan
                if (!isOnline()) {
//                    Toast.makeText(ProgrammingBooks.this, "turn on internet connection", Toast.LENGTH_SHORT).show();
                    Dialog dialog = new Dialog(Novel.this, R.style.NoInternetDialog);
                    dialog.setContentView(R.layout.internet_dialog);
                    dialog.show();
                    TextView retry = dialog.findViewById(R.id.retry);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Novel.this, "Turn on Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Intent novelInt = new Intent(Novel.this, BookSingle.class);
                    novelInt.putExtra("BookName", bookNameNovel[position]);
                    novelInt.putExtra("Author", authorNovel[position]);
                    novelInt.putExtra("ImageBook", imagesNovel[position]);
                    novelInt.putExtra("Url", urlNovel[position]);
                    novelInt.putExtra("Description", descriptionNovel[position]);
                    startActivity(novelInt);

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
// add the book notebook over here.