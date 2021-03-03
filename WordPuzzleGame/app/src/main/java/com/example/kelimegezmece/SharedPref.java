package com.example.kelimegezmece;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    static final String PREF_NAME = "veri";
    static final String PREF_KEY = "key";

    ///her activity den erişilebilir db
    ///ekleme
    public void save(Context context, String bolum, String isim, int puan) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(bolum, puan);
        editor.putString(""+bolum+"isim", isim);
        editor.commit();
    }
    ///veri çekme
    public int getPuan(Context context, String bolum) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //String bolum = settings.getString("bolum", null);
        int puan = settings.getInt(bolum, 0);
        return puan;
    }

    public String getIsim(Context context, String bolum) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //String bolum = settings.getString("bolum", null);
        String isim = settings.getString(""+bolum+"isim", "-");
        return isim;
    }

    ///Kullanici kaydetme
    public void kullanici(Context context, String isim) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_KEY, isim);
        editor.commit();
    }
    public String getkullanici(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //String bolum = settings.getString("bolum", null);
        String isim = settings.getString(PREF_KEY, "");
        return isim;
    }

    ///Bolumu kaydetme
    public void bolum(Context context, int bolum) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("bolum", bolum);
        editor.commit();
    }
    public int getbolum(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //String bolum = settings.getString("bolum", null);
        int bolum = settings.getInt("bolum", 1);
        return bolum;
    }

}
