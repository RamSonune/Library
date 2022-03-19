package com.example.android.mylibaray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://booklist-577f5-default-rtdb.firebaseio.com/");
    SharedPreferences sharedPreferences;
    // this is used to take the state of the checked button .
    // 4
    boolean isRem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phoneLog = findViewById(R.id.phoneLog);
        final EditText userLog = findViewById(R.id.UsernameLog);
        final EditText passwordLog = findViewById(R.id.PassLogin);

        final Button LogButton = findViewById(R.id.LoginButton);
        final Button regnowBtn = findViewById(R.id.regNowLog);

        //1
        CheckBox remember = findViewById(R.id.checkRemember);

        sharedPreferences = getSharedPreferences("SHARED",MODE_PRIVATE);

        //5 : in this we are trying to get the boolean value of the checkbox and if the value is true then we will remember the user for the next login and if false then not remember.
        isRem =sharedPreferences.getBoolean("CheckBox",false);
        // if the value of the chekbox is true then dont make the user to fill the login page again ,send the user to the Main page ..
        if (isRem){
            Intent userRemembered = new Intent(MainActivity.this,Login.class);
            startActivity(userRemembered);
        }

        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneS = phoneLog.getText().toString();
                String userLogS = userLog.getText().toString();
                String passwordS = passwordLog.getText().toString();
                //2
                boolean checkRem = remember.isChecked();

                // attempt to check the internet connection
                if (!isOnline()){
                    Toast.makeText(MainActivity.this, "Please turn on internet connection", Toast.LENGTH_SHORT).show();
                }
                // attempt to check the internet connection


                if (phoneS.isEmpty() || userLogS.isEmpty()|| passwordS.isEmpty()){
                    Toast.makeText(MainActivity.this, "Plz enter your credentials ", Toast.LENGTH_SHORT).show();
                }
                else{
                    //
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // to check that the mobile number is already present in the database or not
                            if (snapshot.hasChild(phoneS)){

                                // we will get in this condition if the phone number exists in the db
                                // now fetch the password from the db to check that the password given by the user is correct or not
                                String fetchPassword = snapshot.child(phoneS).child("password").getValue(String.class);
                                // fetching the data of the UserName from the db to confim that the username entered by the user is correct .
                                String fetchUserid = snapshot.child(phoneS).child("userName").getValue(String.class);
                                if (fetchPassword.equals(passwordS) && fetchUserid.equals(userLogS)){
                                    Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    // this the attempt to store the user credentials into the myaccount page .
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("Phone",phoneS);
                                    editor.putString("UserName",userLogS);
                                    editor.putString("Password",passwordS);
                                    //3
                                    editor.putBoolean("CheckBox",checkRem);
                                    editor.apply();

                                    // if the password is equal then the user can go into the login page
                                    Intent nextIntent = new Intent(MainActivity.this,Login.class);
                                    nextIntent.putExtra("userName" , fetchUserid);
                                    startActivity(nextIntent);
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Wrong Password or UserName", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Wrong Phone Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        regnowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // don't forget to write the Intent of Register page .
                startActivity(new Intent(MainActivity.this , Registration.class));
            }
        });


    }
    // method to check if the internet connection is on or off .
    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()){
            return false;
        }
        return true;
    }
}


