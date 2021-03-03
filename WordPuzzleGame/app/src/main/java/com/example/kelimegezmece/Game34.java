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

public class Game34 extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4, txt5, baslik, bulunan_kelime;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, home, siralama, degistir;
    public int counter, counter_2;
    SharedPref sharedPref;
    Context context = this;
    String isim;
    String textbul;
    String[] harf = {"B","O","D","U","R"};
    String[] kutuda;
    ImageView tik;
    int bulunan_1 = 0, bulunan_2 = 0, bulunan_3=0, bulunan_4=0, bulunan_5=0, bulunan_6=0;
    int bonus = 40, kalan, puan, harf_sayisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        Intent vericek = getIntent();
        kutuda = new String[15];
        sharedPref = new SharedPref();
        sharedPref.bolum(context, 34);
        harf_sayisi = 14;
        isim = vericek.getStringExtra("isim");

        baslik = (TextView) findViewById(R.id.baslik);
        baslik.setText(isim);

        txt1 = (TextView) findViewById(R.id.kelime1);
        txt2 = (TextView) findViewById(R.id.kelime2);
        txt3 = (TextView) findViewById(R.id.kelime3);
        txt4 = (TextView) findViewById(R.id.kelime4);
        txt5 = (TextView) findViewById(R.id.kelime5);

        bulunan_kelime = (TextView) findViewById(R.id.bulunan);
        tik = findViewById(R.id.tik);

        txt1.setOnLongClickListener(longClickListener);
        txt2.setOnLongClickListener(longClickListener);
        txt3.setOnLongClickListener(longClickListener);
        txt4.setOnLongClickListener(longClickListener);
        txt5.setOnLongClickListener(longClickListener);

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
        btn17 = (Button) findViewById(R.id.box_17);
        btn18 = (Button) findViewById(R.id.box_18);
        btn19 = (Button) findViewById(R.id.box_19);
        btn20 = (Button) findViewById(R.id.box_20);
        btn21 = (Button) findViewById(R.id.box_21);
        btn22 = (Button) findViewById(R.id.box_22);
        btn23 = (Button) findViewById(R.id.box_23);
        btn24 = (Button) findViewById(R.id.box_24);
        btn25 = (Button) findViewById(R.id.box_25);

        home = (Button) findViewById(R.id.btn_home);
        siralama = (Button) findViewById(R.id.siralama);
        degistir = (Button) findViewById(R.id.degistir);

        txt1.setText(harf[3]);
        txt2.setText(harf[1]);
        txt3.setText(harf[2]);
        txt4.setText(harf[0]);
        txt5.setText(harf[4]);

        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
        btn6.setVisibility(View.INVISIBLE);
        btn7.setVisibility(View.INVISIBLE);
        btn8.setVisibility(View.VISIBLE);
        btn9.setVisibility(View.INVISIBLE);
        btn10.setVisibility(View.INVISIBLE);
        btn11.setVisibility(View.VISIBLE);
        btn12.setVisibility(View.VISIBLE);
        btn13.setVisibility(View.VISIBLE);
        btn14.setVisibility(View.VISIBLE);
        btn15.setVisibility(View.INVISIBLE);
        btn16.setVisibility(View.INVISIBLE);
        btn17.setVisibility(View.INVISIBLE);
        btn18.setVisibility(View.VISIBLE);
        btn19.setVisibility(View.INVISIBLE);
        btn20.setVisibility(View.INVISIBLE);
        btn21.setVisibility(View.VISIBLE);
        btn22.setVisibility(View.VISIBLE);
        btn23.setVisibility(View.VISIBLE);
        btn24.setVisibility(View.VISIBLE);
        btn25.setVisibility(View.INVISIBLE);

        btn2.setOnDragListener(dragListener_1);
        btn3.setOnDragListener(dragListener_2);
        btn4.setOnDragListener(dragListener_3);
        btn5.setOnDragListener(dragListener_4);
        btn8.setOnDragListener(dragListener_5);
        btn11.setOnDragListener(dragListener_6);
        btn12.setOnDragListener(dragListener_7);
        btn13.setOnDragListener(dragListener_8);
        btn14.setOnDragListener(dragListener_9);
        btn18.setOnDragListener(dragListener_10);
        btn21.setOnDragListener(dragListener_11);
        btn22.setOnDragListener(dragListener_12);
        btn23.setOnDragListener(dragListener_13);
        btn24.setOnDragListener(dragListener_14);

        ////Anasayfaya don
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game34.this, MainActivity.class);
                startActivity(git);
            }
        });

        ////İstatistk gorüntüle
        siralama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent git = new Intent(Game34.this, istatistik.class);
                startActivity(git);
            }
        });

        ////Değiştir
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber;
                Random random = new Random();
                randomNumber = random.nextInt(6);
                if (randomNumber == 0) {
                    txt1.setText(harf[0]);
                    txt2.setText(harf[2]);
                    txt3.setText(harf[1]);
                    txt4.setText(harf[3]);
                    txt5.setText(harf[4]);
                }
                if (randomNumber == 1) {
                    txt1.setText(harf[1]);
                    txt2.setText(harf[3]);
                    txt3.setText(harf[0]);
                    txt4.setText(harf[2]);
                    txt5.setText(harf[4]);
                }
                if (randomNumber == 2) {
                    txt1.setText(harf[2]);
                    txt2.setText(harf[4]);
                    txt3.setText(harf[3]);
                    txt4.setText(harf[0]);
                    txt5.setText(harf[1]);
                }
                if (randomNumber == 3) {
                    txt1.setText(harf[3]);
                    txt2.setText(harf[0]);
                    txt3.setText(harf[4]);
                    txt4.setText(harf[1]);
                    txt5.setText(harf[2]);
                }
                if (randomNumber == 4) {
                    txt1.setText(harf[4]);
                    txt2.setText(harf[3]);
                    txt3.setText(harf[2]);
                    txt4.setText(harf[1]);
                    txt5.setText(harf[0]);
                }
                if (randomNumber == 5) {
                    txt1.setText(harf[0]);
                    txt2.setText(harf[3]);
                    txt3.setText(harf[4]);
                    txt4.setText(harf[1]);
                    txt5.setText(harf[2]);
                }
                if (randomNumber == 6) {
                    txt1.setText(harf[0]);
                    txt2.setText(harf[2]);
                    txt3.setText(harf[4]);
                    txt4.setText(harf[1]);
                    txt5.setText(harf[3]);
                }
            }
        });

        //// Sayaç
        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(35000, 1000) {
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
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
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
                        btn3.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn3.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn3.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn3.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn3.setText(textbul);
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
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn4.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn4.setText(textbul);
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
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn5.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn5.setText(textbul);
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
                        btn8.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn8.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn8.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn8.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn8.setText(textbul);
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
                        btn11.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn11.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn11.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn11.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn11.setText(textbul);
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
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn12.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn12.setText(textbul);
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
                        btn13.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn13.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn13.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn13.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn13.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_9 = new View.OnDragListener() {
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
                        btn14.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn14.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn14.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn14.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn14.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_10 = new View.OnDragListener() {
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
                        btn18.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn18.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn18.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn18.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn18.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_11 = new View.OnDragListener() {
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
                        btn21.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn21.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn21.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn21.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn21.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener_12 = new View.OnDragListener() {
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
                        btn22.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn22.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn22.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn22.setText(textbul);
                    }else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn22.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener_13 = new View.OnDragListener() {
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
                        btn23.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn23.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn23.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn23.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn23.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };
    View.OnDragListener dragListener_14 = new View.OnDragListener() {
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
                        btn24.setText(textbul);
                    } else if (view.getId() == R.id.kelime2) {
                        textbul = txt2.getText().toString();
                        btn24.setText(textbul);
                    } else if (view.getId() == R.id.kelime3) {
                        textbul = txt3.getText().toString();
                        btn24.setText(textbul);
                    } else if (view.getId() == R.id.kelime4) {
                        textbul = txt4.getText().toString();
                        btn24.setText(textbul);
                    } else if (view.getId() == R.id.kelime5) {
                        textbul = txt5.getText().toString();
                        btn24.setText(textbul);
                    }
                    kontrol();
                    break;
            }
            return true;
        }
    };

    public void kontrol() {

        kutuda[1] = btn2.getText().toString();
        kutuda[2] = btn3.getText().toString();
        kutuda[3] = btn4.getText().toString();
        kutuda[4] = btn5.getText().toString();
        kutuda[5] = btn8.getText().toString();
        kutuda[6] = btn11.getText().toString();
        kutuda[7] = btn12.getText().toString();
        kutuda[8] = btn13.getText().toString();
        kutuda[9] = btn14.getText().toString();
        kutuda[10] = btn18.getText().toString();
        kutuda[11] = btn21.getText().toString();
        kutuda[12] = btn22.getText().toString();
        kutuda[13] = btn23.getText().toString();
        kutuda[14] = btn24.getText().toString();

        if (bulunan_1 != 1) {
            if (kutuda[2] == harf[0] && kutuda[5] == harf[1] && kutuda[8] == harf[2] && kutuda[10] == harf[3] && kutuda[13] == harf[4]) {
                bulunan_1 = 1;
                tik("BODUR");
            }
        }
        if (bulunan_2 != 1) {
            if (kutuda[1] == harf[1] && kutuda[2] == harf[0] && kutuda[3] == harf[3] && kutuda[4] == harf[4] ) {
                bulunan_2 = 1;
                tik("OBUR");
            }
        }
        if (bulunan_3 != 1) {
            if (kutuda[6] == harf[1] && kutuda[7] == harf[4] && kutuda[8] == harf[2] && kutuda[9] == harf[3] ) {
                bulunan_3 = 1;
                tik("ORDU");
            }
        }
        if (bulunan_4 != 1) {
            if (kutuda[11] == harf[0] && kutuda[12] == harf[1] && kutuda[13] == harf[4] && kutuda[14] == harf[3] ) {
                bulunan_4 = 1;
                tik("BORU");
            }
        }
        if (bulunan_1 == 1 && bulunan_2 == 1 && bulunan_3 == 1 && bulunan_4 == 1 ) {
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
        String bolum = "16";
        puan = kalan + (harf_sayisi * 5);
        sharedPref.save(context, bolum, isim, puan);
        Intent intent = new Intent(context, Game35.class);
        intent.putExtra("isim", isim);
        startActivity(intent);
    }
}