package com.example.tugcesakarya.saksimuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tugce on 13.09.2017.
 */

public class custom_view_class extends ArrayAdapter<String> {

    private final ArrayList<String> UserCicekAdi;
    private final ArrayList<Bitmap> UserResim;
    private final ArrayList<String> UserCicekAciklama;
    private final Activity context;


    public custom_view_class(ArrayList<String> userCicekAdi, ArrayList<Bitmap> userResim, ArrayList<String> userCicekAciklama, Activity context) {
        super(context, R.layout.custom_view,userCicekAdi);

        this.UserCicekAdi = userCicekAdi;
        this.UserResim = userResim;
        this.UserCicekAciklama = userCicekAciklama;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInf=context.getLayoutInflater();
        View customView=layoutInf.inflate(R.layout.custom_view,null,true);
        TextView userCicekAdi=(TextView) customView.findViewById(R.id.txtCicekAdi);
        ImageView imageView=(ImageView) customView.findViewById(R.id.imgCicekResim);
        TextView userAciklama =(TextView) customView.findViewById(R.id.txtKisaAciklama);

        userCicekAdi.setText(UserCicekAdi.get(position));
        imageView.setImageBitmap(UserResim.get(position));
        userAciklama.setText(UserCicekAciklama.get(position));


        return customView;
    }


}
