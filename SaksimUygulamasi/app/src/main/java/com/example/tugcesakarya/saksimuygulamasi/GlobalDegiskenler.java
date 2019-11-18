package com.example.tugcesakarya.saksimuygulamasi;

/**
 * Created by tugce on 12.09.2017.
 */

public class GlobalDegiskenler {

    public static GlobalDegiskenler instance;
    private String KullaniciAdi;
    private String KullaniciId;
    private String AdSoyad;

    private GlobalDegiskenler(){


    }

    public static GlobalDegiskenler getInstance(){
        if(instance==null){
            instance=new GlobalDegiskenler();

        }
        return  instance;
    }
    public void setAdSoyad(String gelen){ this.AdSoyad=gelen;    }

    public String getAdSoyad(){  return this.AdSoyad;    }


    public void setKullaniciAdi(String gelenAd)
    {
        this.KullaniciAdi=gelenAd;
    }

    public String getKullaniciAdi(){
        return this.KullaniciAdi;
    }



    public void setKullaniciId(String gelenAd)
    {
        this.KullaniciId=gelenAd;
    }

    public String getKullaniciId(){
        return this.KullaniciId;
    }




}
