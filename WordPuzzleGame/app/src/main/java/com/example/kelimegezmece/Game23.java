package com.example.kelimegezmece;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game23 extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4, baslik, bulunan_kelime;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, home, siralama, degistir;
    public int counter, counter_2;
    SharedPref sharedPref;
    Context context = this;
    String isim;
    String textbul;
    String[] harf = {"S", "İ", "T", "E"};
    String[] kutuda;
    ImageView tik;
    int bulunan = 0, bulunan_2 = 0, bulunan_3=0;
    int bonus = 30, kalan, puan, harf_sayisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Intent vericek = getIntent();
        kutuda = new String[8];
        sharedPref = new SharedPref();
        sharedPref.bolum(context, 23);
        harf_sayisi = 8;
        isim = vericek.getStringExtra("isim");

        baslik = (TextView) findViewById(R.id.baslik);
        baslik.setText(isim);

        txt1 = (TextView) findViewById(R.id.kelime1);
        txt2 = (TextView) findViewById(R.id.kelime2);
        txt3 = (TextView) findViewById(R.id.kelime3);
        txt4 = (TextView) findViewById(R.id.kelime4);
        bulunan_kelime = (TextView) findViewById(R.id.bulunan);
        tik = findViewById(R.id.tik);

        txt1.setOnLongClickListener(longClickListener);
        txt2.setOnLongClickListener(longClickListener);
        txt3.setOnLongClickListener(longClickListener);
        txt4.setOnLongClickListener(longClickListener);

        btn1 = (Button) findViewById(R.id.box_1);
        btn2 = (Button) findViewById(R.id.box_2);
        btn3 = (Button) findViewById(R.id.box_3);
        btn4 = (Button) findViewById(R.id.box_4);
        btn5 = (Button) findViewById(R.id.box_5);
        btn6 = (Button) findViewById(R.id.box_6);
        btn7 = (Button) findViewById(R.id.box_7);
        btn8 = (Button) findViewById(R.id.box_8);
        btn9 = (Button) findViewById(R.id.box_9);
        btn10 = (Button) findViewById(R.id.box_10);
        btn11 = (Button) findViewById(R.id.box_11);
        btn12 = (Button) findViewById(R.id.box_12);
        btn13 = (Button) findViewById(R.id.box_13);
        btn14 = (Button) findViewById(R.id.box_14);
        btn15 = (Button) findViewById(R.id.box_15);
        btn16 = (Button) findViewById(R.id.box_16);

        home = (Button) findViewById(R.id.btn_home);
        siralama = (Button) findViewById(R.id.siralama);
        degistir = (Button) findViewById(R.id.degistir);

        txt1.setText(harf[3]);
        txt2.setText(harf[1]);
        txt3.setText(harf[2]);
        txt4.setText(harf[0]);

        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
        btn6.setVisibility(View.VISIBLE);
        btn7.setVisibility(View.VISIBLE);
        btn8.setVisibility(View.VISIBLE);
        btn9.setVisibility(View.INVISIBLE);
        btn10.setVisibility(View.VISIBLE);
        btn11.setVisibility(View.INVISIBLE);
        btn12.setVisibility(View.VISIBLE);
        btn13.setVisibility(View.INVISIBLE);
        btn14.setVisibility(View.INVISIBLE);
        btn15.setVisibility(View.INVISIBLE);
        btn16.setVisibility(View.INVISIBLE);

        btn2.setOnDragListener(dragListener_1);
        btn4.setOnDragListener(dragListener_2);
        btn5.setOnDragListener(dragListener_3);
        btn6.setOnDragListener(dragListener_4);
        btn7.setOnDragListener(dragListener_5);
        btn8.setOnDragListener(dragListener_6);
        btn10.setOnDragListener(dragListener_7);
        btn12.setOnDragListener(dragListener_8);

        ////Anasayfaya don
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game23.this, MainActivity.class);
                startActivity(git);
            }
        });

        ////İstatistk gorüntüle
        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game23.this, istatistik.class);
                startActivity(git);
            }
        });

        ////Değiştir
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber;
                Random random = new Random();
                randomNumber = random.nextInt(5);
                if (randomNumber == 0) {
                    txt1.setText(harf[3]);
                    txt2.setText(harf[2]);
                    txt3.setText(harf[1]);
                    txt4.setText(harf[0]);
                }
                if (randomNumber == 1) {
                    txt1.setText(harf[1]);
                    txt2.setText(harf[3]);
                    txt3.setText(harf[0]);
                    txt4.setText(harf[2]);
                }
                if (randomNumber == 2) {
                    txt1.setText(harf[2]);
                    txt2.setText(harf[0]);
                    txt3.setText(harf[3]);
                    txt4.setText(harf[1]);
                }
                if (randomNumber == 3) {
                    txt1.setText(harf[2]);
                    txt2.setText(harf[1]);
                    txt3.setText(harf[0]);
                    txt4.setText(harf[3]);
                }
                if (randomNumber == 4) {
                    txt1.setText(harf[0]);
                    txt2.setText(harf[3]);
                    txt3.setText(harf[2]);
                    txt4.setText(harf[1]);
                }
            }
        });

        //// Sayaç
        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(30000, 1000) {
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

    View.OnDragListener dragListener_1 = new View.OnDragListener() {
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
                        btn2.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn2.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn2.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn2.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener_2 = new View.OnDragListener() {
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
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn4.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_3 = new View.OnDragListener() {
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
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn5.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_4 = new View.OnDragListener() {
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
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn6.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_5 = new View.OnDragListener() {
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
                        btn7.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn7.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn7.setText(textbul);
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn7.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_6 = new View.OnDragListener() {
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
                        btn8.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn8.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn8.setText(textbul);
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn8.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_7 = new View.OnDragListener() {
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
                        btn10.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn10.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn10.setText(textbul);
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn10.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_8 = new View.OnDragListener() {
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
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn12.setText(textbul);
                    }else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn12.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    public void kontrol() {

        kutuda[0] = btn2.getText().toString();
        kutuda[1] = btn4.getText().toString();
        kutuda[2] = btn5.getText().toString();
        kutuda[3] = btn6.getText().toString();
        kutuda[4] = btn7.getText().toString();
        kutuda[5] = btn8.getText().toString();
        kutuda[6] = btn10.getText().toString();
        kutuda[7] = btn12.getText().toString();

        if(bulunan != 1)
        {
            if (kutuda[2] == harf[0] && kutuda[3] == harf[1] && kutuda[4] == harf[2] && kutuda[5] == harf[3]) {
                bulunan = 1;
                tik("SİTE");
            }
        }
        if(bulunan_2 != 1)
        {
            if (kutuda[0] == harf[0] && kutuda[3] == harf[1] && kutuda[6] == harf[0]) {
                bulunan_2 = 1;
                tik("SİS");
            }
            if (kutuda[0] == harf[0] && kutuda[3] == harf[1] && kutuda[6] == harf[2]) {
                bulunan_2 = 1;
                tik("SİT");
            }
        }
        if(bulunan_3 != 1)
        {
            if (kutuda[1] == harf[0] && kutuda[5] == harf[3] && kutuda[7] == harf[2]) {
                bulunan_3 = 1;
                tik("SET");
            }
        }
        if (bulunan == 1 && bulunan_2 == 1 && bulunan_3 == 1) {
            basarili();
        }
    }
    public void tik(String kelime) {
        bulunan_kelime.setText(kelime);
        bulunan_kelime.setVisibility((View.VISIBLE));
        tik.setVisibility(View.VISIBLE);
        new CountDownTimer(1200, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                kalan = bonus - counter_2;
                counter_2++;
            }
            @Override
            public void onFinish() {
                bulunan_kelime.setVisibility((View.INVISIBLE));
                tik.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void basarili() {
        String bolum = "9";
        puan = kalan + (harf_sayisi * 5);
        sharedPref.save(context, bolum, isim, puan);
        Intent intent = new Intent(context, Game24.class);
        intent.putExtra("isim", isim);
        startActivity(intent);
    }
}