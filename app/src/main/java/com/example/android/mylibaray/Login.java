package com.example.android.mylibaray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer ;
    SliderView sliderView ;
    int [] imageRes = {R.drawable.faultstars,R.drawable.pylogo,R.drawable.apjabdulji,
    R.drawable.roomonefive,R.drawable.thinklikemonk,R.drawable.elon};

    CardView cardViewProg , cardViewCyber ,cardViewDreamer ,cardViewInspire, cardViewMoney ;

    // trying to put the user name in the welcome bar .
    TextView welcome ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logindemo);


        // this all is about the inflating of the auto image slider
        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(imageRes);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        // all above is about the auto image slider

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name_pocket);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        // used to calll the listener on the item of the menu :
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open_nav,R.string.close_nav);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // the one with the card view :
        cardViewProg = findViewById(R.id.card00);
        cardViewProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login.this, "Learn Programming", Toast.LENGTH_SHORT).show();
                Intent progInt = new Intent(Login.this,ProgrammingBooks.class);
                startActivity(progInt);
            }
        });
        // will have to create the card view of the others also and open the new intent which  is going to have the books arraylist .
        cardViewCyber = findViewById(R.id.card01);
        cardViewCyber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cyberInt = new Intent(Login.this,Cyber.class);
                startActivity(cyberInt);
            }
        });

        cardViewDreamer = findViewById(R.id.card10);
        cardViewDreamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dreamerInt = new Intent(Login.this,Novel.class);
                startActivity(dreamerInt);
            }
        });

        cardViewInspire = findViewById(R.id.card11);
        cardViewInspire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login.this, "Inspiration", Toast.LENGTH_SHORT).show();
                Intent inspireInt = new Intent(Login.this,Inspire.class);
                startActivity(inspireInt);
            }
        });

        cardViewMoney = findViewById(R.id.card20);
        cardViewMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paisa = new Intent(Login.this,Money.class);
                startActivity(paisa);

            }
        });


//        welcome = findViewById(R.id.userNavBar);
//        welcome.setText("ram");


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.my_account:
                Toast.makeText(this, "My Account", Toast.LENGTH_SHORT).show();
                Intent miAccount = new Intent(Login.this ,MyAccount.class);
                startActivity(miAccount);
                break;

            // the one with the request
//            case R.id.request:
////                Toast.makeText(this, "request a book", Toast.LENGTH_SHORT).show();
//                Intent reqInt = new Intent(Login.this,Request.class);
//                startActivity(reqInt);
//                break;

            // the one with the share ..
//            case R.id.share:
//                Toast.makeText(this, "share by Bluetooth", Toast.LENGTH_SHORT).show();
//                break;
        }
        // this will make the drawer go back even if one of the above item in the nav bar is clicked.
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}