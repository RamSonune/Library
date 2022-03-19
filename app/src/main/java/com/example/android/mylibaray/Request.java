package com.example.android.mylibaray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Request extends AppCompatActivity {
    EditText bookNameEmail, noOfBooks;
    Button submit;
    String nameOfBookEmailS;
    String noOfBookEmailS;

    // this is for the purpose of the number of the books
    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        bookNameEmail = findViewById(R.id.bookEmail);
        noOfBooks = findViewById(R.id.noBookEmail);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberOfbook = giveNumber();

                if (numberOfbook >= 10) {
                    Toast.makeText(Request.this, "Cant order more then 10 books", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "MyLibrary App");
                intent.putExtra(Intent.EXTRA_TEXT, "Name of book : " + giveName() +
                        "\nNO of books = " + giveNo());
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ram@gmail.com"});
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });


    }

    // this method is used to give the name of the book , so that this can be used in the intent of the email
    private String giveName() {
        nameOfBookEmailS = bookNameEmail.getText().toString();
        return nameOfBookEmailS;

    }

    // this method will be usefull to send the
    private String giveNo() {
        noOfBookEmailS = noOfBooks.getText().toString();
        return noOfBookEmailS;
    }

    private int giveNumber() {
        number = Integer.parseInt(noOfBooks.getText().toString());
        return number;
    }

}