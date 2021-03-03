package com.example.kelimegezmece;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    TextView txt1, txt2, txt3, baslik;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,  home, siralama, degistir;
    public int counter, counter_2;
    SharedPref sharedPref;
    Context context = this;
    String isim;
    String textbul;
    String[] harf = {"K", "A", "R"};
    Boolean[] temp_1 = {false, false, false};
    Boolean[] temp_2 = {false, false, false};
    ImageView tik;
    int bulunan=0;
    int bonus = 20, kalan, puan, harf_sayisi;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent vericek = getIntent();
        isim = vericek.getStringExtra("isim");

        baslik = (TextView) findViewById(R.id.baslik);
        baslik.setText(isim);

        txt1 = (TextView) findViewById(R.id.kelime1);
        txt2 = (TextView) findViewById(R.id.kelime2);
        txt3 = (TextView) findViewById(R.id.kelime3);
        tik = findViewById(R.id.tik);

        txt1.setOnLongClickListener(longClickListener);
        txt2.setOnLongClickListener(longClickListener);
        txt3.setOnLongClickListener(longClickListener);

        btn1 = (Button) findViewById(R.id.box_1);
        btn2 = (Button) findViewById(R.id.box_2);
        btn3 = (Button) findViewById(R.id.box_3);
        btn4 = (Button) findViewById(R.id.box_4);
        btn5 = (Button) findViewById(R.id.box_5);
        btn6 = (Button) findViewById(R.id.box_6);
        btn7 = (Button) findViewById(R.id.box_7);
        btn8 = (Button) findViewById(R.id.box_8);
        btn9 = (Button) findViewById(R.id.box_9);

        home = (Button) findViewById(R.id.btn_home);
        siralama = (Button) findViewById(R.id.siralama);
        degistir = (Button) findViewById(R.id.degistir);

        btn4.setOnDragListener(dragListener);
        btn5.setOnDragListener(dragListener_2);
        btn6.setOnDragListener(dragListener_3);

        txt1.setText(harf[2]);
        txt2.setText(harf[0]);
        txt3.setText(harf[1]);

        sharedPref = new SharedPref();
        sharedPref.bolum(context, 1);

        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
        btn6.setVisibility(View.VISIBLE);
        btn7.setVisibility(View.INVISIBLE);
        btn8.setVisibility(View.INVISIBLE);
        btn9.setVisibility(View.INVISIBLE);
        harf_sayisi=3;

        ////Anasayfaya don
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game.this, MainActivity.class);
                startActivity(git);
            }
        });

        ////İstatistk gorüntüle
        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game.this, istatistik.class);
                startActivity(git);
            }
        });

        ////Değiştir
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber;
                Random random = new Random();
                randomNumber = random.nextInt(4);
                if (randomNumber == 0) {
                    txt1.setText(harf[0]);
                    txt2.setText(harf[2]);
                    txt3.setText(harf[1]);
                }
                if (randomNumber == 1) {
                    txt1.setText(harf[1]);
                    txt2.setText(harf[0]);
                    txt3.setText(harf[2]);
                }
                if (randomNumber == 2) {
                    txt1.setText(harf[1]);
                    txt2.setText(harf[2]);
                    txt3.setText(harf[0]);
                }
                if (randomNumber == 3) {
                    txt1.setText(harf[2]);
                    txt2.setText(harf[1]);
                    txt3.setText(harf[0]);
                }
            }
        });

        Toast.makeText(Game.this, "Bol Şans!", Toast.LENGTH_SHORT).show();

        //// Sayaç
        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                kalan = bonus - counter;
                counttime.setText(String.valueOf(kalan));
                counter++;
            }

            @Override
            public void onFinish() {
                counttime.setText(":(");
            }
        }.start();
    }

    ///Drag&Drop Kısmı
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(data, myShadowBuilder, v, 0);
            } else {
                v.startDrag(data, myShadowBuilder, v, 0);
            }
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent_1 = event.getAction();

            switch (dragEvent_1) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.kelime1) {
                        textbul = txt1.getText().toString();
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn4.setText(textbul);
                    }
                    if (btn4.getText().toString() == harf[0]) {
                        temp_1[0] = true;
                        kontrol(temp_1[0], temp_1[1], temp_1[2]);
                    } else {
                        temp_1[0] = false;
                    }
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_2 = new View.OnDragListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent_1 = event.getAction();

            switch (dragEvent_1) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.kelime1) {
                        textbul = txt1.getText().toString();
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn5.setText(textbul);
                    }
                    if (btn5.getText().toString() == harf[1]) {
                        temp_1[1] = true;
                        kontrol(temp_1[0], temp_1[1], temp_1[2]);
                    } else {
                        temp_1[1] = false;
                    }
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_3 = new View.OnDragListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent_1 = event.getAction();

            switch (dragEvent_1) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.kelime1) {
                        textbul = txt1.getText().toString();
                        btn6.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn6.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn6.setText(textbul);
                    }
                    if (btn6.getText().toString() == harf[2]) {
                        temp_1[2] = true;
                        kontrol(temp_1[0], temp_1[1], temp_1[2]);
                    } else {
                        temp_1[2] = false;
                    }
                    break;
            }
            return true;
        }
    };


    ///Cevap Kontrol
    public void kontrol(boolean temp_1, boolean temp_2, boolean temp_3) {
        if (temp_1 == true && temp_2 == true && temp_3 == true) {

            tik.setVisibility(View.VISIBLE);
            new CountDownTimer(1200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    kalan = bonus - counter_2;
                    counter_2++;
                }

                @Override
                public void onFinish() {
                    tik.setVisibility(View.INVISIBLE);
                }
            }.start();

            String bolum = "1";
            bulunan++;
            if(bulunan>=1)
            {
                puan = kalan+(harf_sayisi*5);
                sharedPref.save(context, bolum, isim, puan);
                Intent intent = new Intent(context, Game2.class);
                intent.putExtra("isim", isim);
                startActivity(intent);
            }

        }
    }

}
