package com.example.kelimegezmece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class istatistik extends AppCompatActivity {

    SharedPref sharedPref;
    Context context = this;
    String[] kullanici;
    Button home, siralama;
    int[] deger;
    int i;
    TextView[] txt;
    TextView[] kln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istatistik);

        kullanici = new String[19];
        deger = new int[19];
        txt = new TextView[19];
        kln = new TextView[19];
        sharedPref = new SharedPref();

        home = (Button) findViewById(R.id.btn_home);
        siralama = (Button) findViewById(R.id.siralama);

        txt[1] = (TextView) findViewById(R.id.txt1);
        kln[1] = (TextView) findViewById(R.id.kln1);
        txt[2] = (TextView) findViewById(R.id.txt2);
        kln[2] = (TextView) findViewById(R.id.kln2);
        txt[3] = (TextView) findViewById(R.id.txt3);
        kln[3] = (TextView) findViewById(R.id.kln3);
        txt[4] = (TextView) findViewById(R.id.txt4);
        kln[4] = (TextView) findViewById(R.id.kln4);
        kln[5] = (TextView) findViewById(R.id.kln5);
        txt[5] = (TextView) findViewById(R.id.txt5);
        kln[6] = (TextView) findViewById(R.id.kln6);
        txt[6] = (TextView) findViewById(R.id.txt6);
        txt[7] = (TextView) findViewById(R.id.txt21);
        kln[7] = (TextView) findViewById(R.id.kln21);
        txt[8] = (TextView) findViewById(R.id.txt22);
        kln[8] = (TextView) findViewById(R.id.kln22);
        txt[9] = (TextView) findViewById(R.id.txt23);
        kln[9] = (TextView) findViewById(R.id.kln23);
        txt[10] = (TextView) findViewById(R.id.txt24);
        kln[10] = (TextView) findViewById(R.id.kln24);
        kln[11] = (TextView) findViewById(R.id.kln25);
        txt[11] = (TextView) findViewById(R.id.txt25);
        kln[12] = (TextView) findViewById(R.id.kln26);
        txt[12] = (TextView) findViewById(R.id.txt26);
        txt[13] = (TextView) findViewById(R.id.txt31);
        kln[13] = (TextView) findViewById(R.id.kln31);
        txt[14] = (TextView) findViewById(R.id.txt32);
        kln[14] = (TextView) findViewById(R.id.kln32);
        kln[15] = (TextView) findViewById(R.id.kln33);
        txt[15] = (TextView) findViewById(R.id.txt33);
        kln[16]= (TextView) findViewById(R.id.kln34);
        txt[16] = (TextView) findViewById(R.id.txt34);
        txt[17] = (TextView) findViewById(R.id.txt35);
        kln[17] = (TextView) findViewById(R.id.kln35);
        txt[18] = (TextView) findViewById(R.id.txt36);
        kln[18] = (TextView) findViewById(R.id.kln36);

        for(i=1; i<19; i++)
        {
            kullanici[i] = sharedPref.getIsim(context, ""+i+"");
            deger[i] = sharedPref.getPuan(context, ""+i+"");
            txt[i] .setText("" + deger[i]  + "");
            kln[i] .setText("" + kullanici[i]  + " ");
        }

        ////Anasayfaya don
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(istatistik.this, MainActivity.class);
                startActivity(git);
            }
        });

        ////İstatistk gorüntüle
        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(istatistik.this, istatistik.class);
                startActivity(git);
            }
        });
    }
}
