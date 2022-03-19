package com.example.android.mylibaray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookSingle extends AppCompatActivity {

    CollapsingToolbarLayout toolbarLayout;
    ImageView imageViewBook;
    FloatingActionButton download , view ;
    TextView des ;

    String urlView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single);

        toolbarLayout = findViewById(R.id.collapsing_toolbar);
        imageViewBook = findViewById(R.id.singleBookImage);
        download = findViewById(R.id.floatingDownload);
        view = findViewById(R.id.floatingView);
        des = findViewById(R.id.desBook);

        Intent intent = this.getIntent();
        if (intent != null){
            String nameOfBook = intent.getStringExtra("BookName");
            String authorOfBook = intent.getStringExtra("Author");
            int imageOfBook = intent.getIntExtra("ImageBook",R.drawable.android);
            String desSingle = intent.getStringExtra("Description");
            urlView = intent.getStringExtra("Url");

            toolbarLayout.setTitle(nameOfBook);
            imageViewBook.setImageResource(imageOfBook);
            des.setText(desSingle);

        }


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if offline then give the toast or else download the book by calling the function of the download respective book which is given below ie goToTheUrl..
                if (!isOnline()){
                    Toast.makeText(BookSingle.this, "Please Turn on Wifi or Mobile Data", Toast.LENGTH_LONG).show();
                }
                else {
                    goToTheUrl(urlView);
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prompt the user to on the internet if it is off .. or else let the further procedure go as coded .
                if (!isOnline()){
                    Toast.makeText(BookSingle.this, "Please Turn on Wifi or Mobile Data", Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(BookSingle.this, "view me", Toast.LENGTH_SHORT).show();
                else {
                    Intent viewInt = new Intent(BookSingle.this, Books.class);
                    // we can also give the name of the url over here as the Url only but that name is already occupied by the main list view which contains all the books , thus there may be a conflict
                    // of the Url and the Url in this class  and the Programming books class .
                    viewInt.putExtra("UrlView", urlView);
                    startActivity(viewInt);
                }
            }
        });
    }
    private void goToTheUrl(String url){
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    // method to check the internet connection of the user .
    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()){
            return false ;
        }
        return true;
    }
}

//the one with the gradient of the midnight city
/*
#232526
        →
        #414345

 */

// shroom haze the one with the purple
//#5c258d
//        →
//        #4389a2

// cherry red

//#eb3349
//        →
//        #f45c43

// ocean blue
//#1a2980
//        →
//        #26d0ce

// ver black -- may suit up with the combination of the true black :
// the first hex is very important over here .. a little grey
//#f7f8f8
//        →
//        #acbb78