package com.example.android.mylibaray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyAccount extends AppCompatActivity {
    TextView myusername , myPhone , myPassword ;
    SharedPreferences sharedPreferences ;
    Button logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        myusername = findViewById(R.id.UserNameMy);
        myPhone = findViewById(R.id.PhoenMy);
        myPassword = findViewById(R.id.password_my_account);
        logout = findViewById(R.id.logoutMy);

        sharedPreferences = getSharedPreferences("SHARED",MODE_PRIVATE);

        String myUserS = sharedPreferences.getString("UserName","");
        String myAccountPhone = sharedPreferences.getString("Phone","");
        String myAccountPass = sharedPreferences.getString("Password","");

        // setting the name and the user name as the one which is given by the shared preferences ;
        myusername.setText(myUserS);
        myPhone.setText(myAccountPhone);
        myPassword.setText(myAccountPass);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // give the box  to fill the info again if the user logouts
                Intent loginInt = new Intent(MyAccount.this,MainActivity.class);

                startActivity(loginInt);
                finish();
            }
        });

    }
}