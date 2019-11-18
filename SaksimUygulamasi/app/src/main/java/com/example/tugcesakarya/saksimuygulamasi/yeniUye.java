package com.example.tugcesakarya.saksimuygulamasi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class yeniUye extends AppCompatActivity {

    EditText AdSoyad;
    EditText Email;
    EditText Kadi;
    EditText Sifre;
    EditText SifreTekrari;
    Button Kayitol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_uye);

        AdSoyad=(EditText) findViewById(R.id.txtAdSoyad);
        Email=(EditText) findViewById(R.id.txtEmail);
        Kadi=(EditText) findViewById(R.id.txtKadi);
        Sifre=(EditText) findViewById(R.id.txtSifre1);
        SifreTekrari=(EditText) findViewById(R.id.txtSifreTekrari);
        Kayitol =(Button) findViewById(R.id.btnKayit);

        Kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AdSoyad.getText().toString().trim().equals("")){

                    Toast.makeText(yeniUye.this, "Ad Soyad Boş Geçilemez.", Toast.LENGTH_SHORT).show();


                }else
                {

                    if(Email.getText().toString().trim().equals("")){

                        Toast.makeText(yeniUye.this, "Email Boş Geçilemez.", Toast.LENGTH_SHORT).show();


                    }else
                    {

                        if(Kadi.getText().toString().trim().equals("")){

                            Toast.makeText(yeniUye.this, "Kullanıcı Adı Boş Geçilemez.", Toast.LENGTH_SHORT).show();


                        }else
                        {

                            if(Sifre.getText().toString().trim().equals("") && Sifre.getText().toString().trim().length()<6){

                                Toast.makeText(yeniUye.this, "Şifreniz En Az 6 Karakter Olmalıdır.", Toast.LENGTH_SHORT).show();


                            }else
                            {

                                if( !Sifre.getText().toString().equalsIgnoreCase(SifreTekrari.getText().toString())){

                                    Toast.makeText(yeniUye.this, "Şifreniz Uyuşmuyor.", Toast.LENGTH_SHORT).show();


                                }else
                                {

                                    String asAdSoyad=AdSoyad.getText().toString();
                                    String asEmail=Email.getText().toString();
                                    String asKadi=Kadi.getText().toString();
                                    String asSifre = Sifre.getText().toString();

                                    ContentValues con = new ContentValues();
                                    con.put("AdSoyad",asAdSoyad);
                                    con.put("Email",asEmail);
                                    con.put("Kadi",asKadi);
                                    con.put("Sifre",asSifre);

                                    veriEkle("User",yeniUye.this,con);
                                    Toast.makeText(yeniUye.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                                    final Thread tr = new Thread(){
                                        @Override
                                        public void run() {
                                            try{

                                                sleep(2000);
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
                        }
                    }
                }
            }
        });

    }



    public  void veriEkle(String tableName, Context context, ContentValues values)
    {

        DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.CreateDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı oluşturulamadı ve kopyalanamadı!");
        }



        SQLiteDatabase myDb=dbHelper.getWritableDatabase();
        myDb.insert(tableName,null,values);
        myDb.close();

    }
}
