package com.example.tugcesakarya.saksimuygulamasi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Giris extends AppCompatActivity {

    EditText Kadi;
    EditText Sifre;
    Button GirisYap;
    Button YeniUye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giris);




       final DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.CreateDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı oluşturulamadı ve kopyalanamadı!");
        }


       Kadi =(EditText) findViewById(R.id.txtKadi);
        Sifre=(EditText) findViewById(R.id.txtSifre);
        GirisYap=(Button) findViewById(R.id.btnGiris);
        YeniUye =(Button) findViewById(R.id.btnyeni);

        YeniUye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),yeniUye.class);
                startActivity(intent);
            }
        });


        GirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getKad=Kadi.getText().toString();
                String getSifre=Sifre.getText().toString();

                SQLiteDatabase db=dbHelper.getReadableDatabase();
                String[] getColumnName={"Id,AdSoyad"};
                Cursor imlec=db.query("User", getColumnName, "Kadi = ? and Sifre = ?", new String[]{ getKad,getSifre }, null, null, null);


                if (imlec.moveToNext()){
                    String UserId=imlec.getString(imlec.getColumnIndex("Id"));
                    String UserAdSoyad=imlec.getString(imlec.getColumnIndex("AdSoyad"));

                    GlobalDegiskenler gd=GlobalDegiskenler.getInstance();
                    gd.setAdSoyad(UserAdSoyad);
                    gd.setKullaniciId(UserId);
                    gd.setKullaniciAdi(Kadi.getText().toString());

                    Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
                    startActivity(intent);


                }else {
                    Toast.makeText(getApplicationContext(),"Giriş Başarısız", Toast.LENGTH_SHORT).show();
                }



                db.close();
            }
        });




    }
}
