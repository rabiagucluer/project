package com.example.tugcesakarya.saksimuygulamasi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AnaSayfa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> cicekAdiListe;
    ArrayList<String> cicekAciklamaList;
    ArrayList<Bitmap> cicekResimList;
    ArrayList<String> Uid;
    ListView liste;
    custom_view_class adapter;


    Toolbar toolbar;
    private static final int DIALOG_REALLY_EXIT_ID = 0;
    GlobalDegiskenler gd = GlobalDegiskenler.getInstance();
    DrawerLayout mDraw;
    NavigationView navigationView;
    TextView baslik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Saksım");

        navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mDraw =(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle =new ActionBarDrawerToggle(this,mDraw,toolbar,R.string.a,R.string.b);
        mDraw.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        CicekleriGetir();

    }

    public void CicekleriGetir(){
        cicekAdiListe=new ArrayList<String>();
        cicekAciklamaList = new ArrayList<String>();
        cicekResimList = new ArrayList<Bitmap>();
        Uid = new ArrayList<String>();

        adapter = new custom_view_class(cicekAdiListe,cicekResimList,cicekAciklamaList,this);
        liste = (ListView) findViewById(R.id.lslist);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),CicekGoster.class);
                intent.putExtra("Uid",Uid.get(position));
                startActivity(intent);


            }
        });
        DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.CreateDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı oluşturulamadı ve kopyalanamadı!");
        }


        //Proje içine kopyalanmış olan veritabanımızdan verileri listview e yazdırdık

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] getColumnName={"Id,CiceginAdi,CiceginAnlami,CiceginResmi"};
        Cursor imlec=db.query("Saksim", getColumnName, null, null, null, null, null);
        String x="splash";

        while(imlec.moveToNext()){
            String gelenCicekAdi=imlec.getString(imlec.getColumnIndex("CiceginAdi"));
            byte[] bytArray = imlec.getBlob(imlec.getColumnIndex("CiceginResmi"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytArray,0,bytArray.length);


            String gelenId=imlec.getString(imlec.getColumnIndex("Id"));
            String gelenCicekAciklama=imlec.getString(imlec.getColumnIndex("CiceginAnlami"));
            cicekAdiListe.add(gelenCicekAdi);
            cicekAciklamaList.add(gelenCicekAciklama);
            cicekResimList.add(bitmap);
            Uid.add(gelenId);
            adapter.notifyDataSetChanged();

        }

        imlec.close();
        db.close();



    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        navigationViewKapat();

        switch (item.getItemId()) {
            case 0:
                break;
            case 1:break;
            case 2:break;
            case R.id.nav_privacy_policy:{
                showDialog(DIALOG_REALLY_EXIT_ID);
                break;
            }
            default:break;
        }

        return true;
    }

    private void navigationViewKapat() {
        mDraw.closeDrawer(GravityCompat.START);
    }
    private void navigationViewAc() {
        mDraw.openDrawer(GravityCompat.START);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        final Dialog dialog1;
        switch(id) {
            case DIALOG_REALLY_EXIT_ID:
                dialog1 = new AlertDialog.Builder(this).setMessage(
                        "Uygulamadan çıkmak istiyor musunuz?")
                        .setCancelable(false)
                        .setPositiveButton("Evet",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        AnaSayfa.this.finish();
                             /*Burda kullanıcı evet butonuna bastığında uygulamadan kontrollü bir şekilde çıkmış oluyor.*/
                                    }
                                })
                        .setNegativeButton("Hayır",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
          /*Burada da kullanıcı hayır butonuna bastığında dialogu dismiss ederek(dialog.cancel())kullanıcının uygulamaya tekrar dönmesini sağlıyoruz.*/
                                    }
                                }).create();
                break;
            default:
                dialog1 = null;
        }
        return dialog1;
    }



    @Override
    public void onBackPressed() {
        if(mDraw.isDrawerOpen(GravityCompat.START)){
            navigationViewKapat();

        }else{

            showDialog(DIALOG_REALLY_EXIT_ID);

        }

    }
}
