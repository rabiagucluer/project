package com.example.tugcesakarya.saksimuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        final Thread tr = new Thread(){
            @Override
            public void run() {
                try{

                    sleep(3000);
                    Intent i = new Intent(getApplicationContext(),Giris.class);
                    startActivity(i);

                }catch (InterruptedException e){



                }finally {
                    finish();
                }
            }
        };
        tr.start();
    }
}
