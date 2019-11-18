package com.example.tugcesakarya.saksimuygulamasi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CicekGoster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cicek_goster);

        Intent intent = getIntent();

        String Uid=intent.getStringExtra("Uid");
        ImageView img= (ImageView) findViewById(R.id.imageView);
        TextView apCiceginAdi=(TextView) findViewById(R.id.txtCicekAdi);
        TextView apCiceginAnlami=(TextView) findViewById(R.id.txtAnlami);
        TextView apAnavatani=(TextView) findViewById(R.id.txtAnavatani);
        TextView apYetistigiMevsim=(TextView) findViewById(R.id.txtMevsim);
        TextView apRenkleri=(TextView) findViewById(R.id.txtRenkleri);
        TextView apTurSayisi=(TextView) findViewById(R.id.txtTurSayisi);
        TextView apSulama=(TextView) findViewById(R.id.txtSulama);
        TextView apBudama=(TextView) findViewById(R.id.txtBudama);
        TextView apGubreVitamin=(TextView) findViewById(R.id.txtGubreVitamin);
        TextView apTopragininDegisimi=(TextView) findViewById(R.id.txtTopragininDegisimi);
        TextView apSaksisininDegisimi=(TextView) findViewById(R.id.txtSaksisininDegisimi);
        TextView apBarindirilmaOzellikleri=(TextView) findViewById(R.id.txtBarindirilmaOzellikleri);
        TextView apCogaltilmasi=(TextView) findViewById(R.id.txtCogaltilmasi);
        TextView apZararVerenSeyler=(TextView) findViewById(R.id.txtZararVerenSeyler);
        TextView apKullanildigiYerler=(TextView) findViewById(R.id.txtKullanildigiYerler);


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
        String[] getColumnName={"CiceginAdi,CiceginAnlami,CiceginResmi,Anavatani,YetistigiMevsim,Renkleri,TurSayisi,Sulama,Budama,GubreVitamin,TopragininDegisimi,SaksisininDegisimi,BarindirilmaOzellikleri,Cogaltilmasi,ZararVerenSeyler,KullanildigiYerler"};
        Cursor imlec=db.query("Saksim", getColumnName, "Id = ? ", new String[]{ Uid }, null, null, null);

        while(imlec.moveToNext()){

            byte[] bytArray = imlec.getBlob(imlec.getColumnIndex("CiceginResmi"));
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytArray,0,bytArray.length);

            String CiceginAdi=imlec.getString(imlec.getColumnIndex("CiceginAdi"));
            String CiceginAnlami=imlec.getString(imlec.getColumnIndex("CiceginAnlami"));
            String Anavatani=imlec.getString(imlec.getColumnIndex("Anavatani"));
            String YetistigiMevsim=imlec.getString(imlec.getColumnIndex("YetistigiMevsim"));
            String Renkleri=imlec.getString(imlec.getColumnIndex("Renkleri"));
            String TurSayisi=imlec.getString(imlec.getColumnIndex("TurSayisi"));
            String Sulama=imlec.getString(imlec.getColumnIndex("Sulama"));
            String Budama=imlec.getString(imlec.getColumnIndex("Budama"));
            String GubreVitamin=imlec.getString(imlec.getColumnIndex("GubreVitamin"));
            String TopragininDegisimi=imlec.getString(imlec.getColumnIndex("TopragininDegisimi"));
            String SaksisininDegisimi=imlec.getString(imlec.getColumnIndex("SaksisininDegisimi"));
            String BarindirilmaOzellikleri=imlec.getString(imlec.getColumnIndex("BarindirilmaOzellikleri"));
            String Cogaltilmasi=imlec.getString(imlec.getColumnIndex("Cogaltilmasi"));
            String ZararVerenSeyler=imlec.getString(imlec.getColumnIndex("ZararVerenSeyler"));
            String KullanildigiYerler=imlec.getString(imlec.getColumnIndex("KullanildigiYerler"));

            img.setImageBitmap(bitmap);
            apCiceginAdi.setText(CiceginAdi);
            apCiceginAnlami.setText(CiceginAnlami);
            apAnavatani.setText(Anavatani);
            apYetistigiMevsim.setText(YetistigiMevsim);
            apRenkleri.setText(Renkleri);
            apTurSayisi.setText(TurSayisi);
            apSulama.setText(Sulama);
            apBudama.setText(Budama);
            apGubreVitamin.setText(GubreVitamin);
            apTopragininDegisimi.setText(TopragininDegisimi);
            apSaksisininDegisimi.setText(SaksisininDegisimi);
            apBarindirilmaOzellikleri.setText(BarindirilmaOzellikleri);
            apCogaltilmasi.setText(Cogaltilmasi);
            apZararVerenSeyler.setText(ZararVerenSeyler);
            apKullanildigiYerler.setText(KullanildigiYerler);

        }

        imlec.close();
        db.close();
    }
}
