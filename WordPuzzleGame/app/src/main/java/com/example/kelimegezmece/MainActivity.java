package com.example.kelimegezmece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText edtAd;
    Button btnGonder, reset, siralama;
    CheckBox hatirla;
    SharedPref sharedPref;
    Context context = this;
    String kullanici;
    int bolum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAd = findViewById(R.id.txt_isim);
        btnGonder = findViewById(R.id.btn_giris);
        hatirla = findViewById(R.id.unutma);
        reset = (Button) findViewById(R.id.reset);
        siralama = (Button) findViewById(R.id.siralama);
        sharedPref = new SharedPref();

        kullanici = sharedPref.getkullanici(context);
        edtAd.setText(kullanici);

        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(MainActivity.this, istatistik.class);
                startActivity(git);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.bolum(context, 1);
                Toast.makeText(MainActivity.this, "Oyun başarıyla sıfırlandı!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isim = edtAd.getText().toString();
                bolum = sharedPref.getbolum(context);

                if(true==hatirla.isChecked());
                {
                    sharedPref.kullanici(context,isim);
                }
                Intent git = new Intent();
                if(bolum==1){ git = new Intent(MainActivity.this, Game.class);}
                if(bolum==2){ git = new Intent(MainActivity.this, Game2.class);}
                if(bolum==3){ git = new Intent(MainActivity.this, Game3.class);}
                if(bolum==4){ git = new Intent(MainActivity.this, Game4.class);}
                if(bolum==5){ git = new Intent(MainActivity.this, Game5.class);}
                if(bolum==6){ git = new Intent(MainActivity.this, Game6.class);}
                if(bolum==21){ git = new Intent(MainActivity.this, Game21.class);}
                if(bolum==22){ git = new Intent(MainActivity.this, Game22.class);}
                if(bolum==23){ git = new Intent(MainActivity.this, Game23.class);}
                if(bolum==24){ git = new Intent(MainActivity.this, Game24.class);}
                if(bolum==25){ git = new Intent(MainActivity.this, Game25.class);}
                if(bolum==26){ git = new Intent(MainActivity.this, Game26.class);}
                if(bolum==31){ git = new Intent(MainActivity.this, Game31.class);}
                if(bolum==32){ git = new Intent(MainActivity.this, Game32.class);}
                if(bolum==33){ git = new Intent(MainActivity.this, Game33.class);}
                if(bolum==34){ git = new Intent(MainActivity.this, Game34.class);}
                if(bolum==35){ git = new Intent(MainActivity.this, Game35.class);}
                if(bolum==36){ git = new Intent(MainActivity.this, istatistik.class);}


                git.putExtra("isim", isim);
                startActivity(git);
            }
        });


    }
}
