package com.example.android.mylibaray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://booklist-577f5-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText fullNameReg = findViewById(R.id.NameReg);
        final EditText userNameReg = findViewById(R.id.userReg);
        final EditText emailReg = findViewById(R.id.emailReg);
        final EditText passwordReg = findViewById(R.id.passReg);
        final EditText passwordConReg = findViewById(R.id.passConReg);
        final EditText phoneReg = findViewById(R.id.phoneReg);

        String [] nameCheck = {"0","1","2","3","4","5","6","7","8","9"};
        String beingChecked ;


        final Button Register = findViewById(R.id.RegisterButton);
        final Button LogInReg = findViewById(R.id.LoginReg);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fetching the data from the edit text so that we can do the string methods on it and see that the data enter by the user is accurate or not .
                String fullNameS = fullNameReg.getText().toString();
                String userNameS = userNameReg.getText().toString();
                String emailS = emailReg.getText().toString();
                String passwordS = passwordReg.getText().toString();
                String passwordConS = passwordConReg.getText().toString();
                String phoneS = phoneReg.getText().toString();

                // attempt to check the internet connection of the user .
                if (!isOnline()){
                    Toast.makeText(Registration.this, "Please turn on Mobile Data", Toast.LENGTH_SHORT).show();
                }


                int i =0 ;

                if (fullNameS.isEmpty() || userNameS.isEmpty() || emailS.isEmpty() || passwordS.isEmpty() || passwordConS.isEmpty()){
                    Toast.makeText(Registration.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else if (phoneS.length() < 10){
                    Toast.makeText(Registration.this, "Give accurate phone number", Toast.LENGTH_SHORT).show();
                }
                // this will be used to determine that the phone number is not greater then 10
                else if (phoneS.length()>10){
                    Toast.makeText(Registration.this, "Please Give accurate phone number", Toast.LENGTH_SHORT).show();
                }
                else if (!passwordS.equals(passwordConS)){
                    Toast.makeText(Registration.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else if (!emailS.contains("@")){
                    Toast.makeText(Registration.this, "Add proper email id", Toast.LENGTH_SHORT).show();
                }

                else if (!emailS.endsWith("com")){
                    Toast.makeText(Registration.this, "Please give accurate email id", Toast.LENGTH_SHORT).show();
                }
                else if (fullNameS.startsWith("1") || fullNameS.startsWith("2") || fullNameS.startsWith("3") || fullNameS.startsWith("4") || fullNameS.startsWith("5")
                        || fullNameS.startsWith("6") || fullNameS.startsWith("7")|| fullNameS.startsWith("8") || fullNameS.startsWith("9") ||fullNameS.startsWith("0")) {
                    Toast.makeText(Registration.this, "dont add number in your name", Toast.LENGTH_SHORT).show();
                }
                else if (fullNameS.endsWith("1") || fullNameS.endsWith("2") || fullNameS.endsWith("3") || fullNameS.endsWith("4") || fullNameS.endsWith("5")
                        || fullNameS.endsWith("6") || fullNameS.endsWith("7")|| fullNameS.endsWith("8") || fullNameS.endsWith("9") ||fullNameS.endsWith("0")) {
                    Toast.makeText(Registration.this, "dont add number in your name", Toast.LENGTH_SHORT).show();
                }
                else if (fullNameS.contains("1") || fullNameS.contains("2") || fullNameS.contains("3") || fullNameS.contains("4") || fullNameS.contains("5") || fullNameS.contains("6")
                        || fullNameS.contains("7") || fullNameS.contains("8") || fullNameS.contains("9") || fullNameS.contains("0")){
                    Toast.makeText(Registration.this, "Please only add your name and not number", Toast.LENGTH_SHORT).show();
                }



//                else if (fullNameS.st)

                else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // to check if the db has the already registered email id.
                            if (snapshot.hasChild(phoneS)){
                                Toast.makeText(Registration.this, "Account is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                // sending the current information of the user provided details to the firebase
                                databaseReference.child("users").child(phoneS).child("fullname").setValue(fullNameS);
                                databaseReference.child("users").child(phoneS).child("userName").setValue(userNameS);
                                databaseReference.child("users").child(phoneS).child("email").setValue(emailS);
                                databaseReference.child("users").child(phoneS).child("password").setValue(passwordS);

                                Toast.makeText(Registration.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }



            }
        });

        LogInReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });


    }

    // to check the internet connection :
    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()){
            return  false;
        }
        return true;
    }

}




