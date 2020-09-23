package com.english.myapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; // переменныяя для картинки + текст
    public int numRight; // переменная для картинки + текст
    Array array = new Array(); // создание левой картинки
    Random random = new Random(); // генерация случайных чисел
    public int count = 0; // счетчик правильных чисел

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // код который скругляет углы левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // код который скругляет углы правой картинки
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        // развервернуть игру на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        // Вызов диалогового окна в начале
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // окно нельзя закрыть кнопкой "Назад"

        // Кнопка дял закрытия диалоговое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // обрабатываем нажатие кнопки
                try{
                    // вернуться назад к выбору уровки
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish(); // закрыть этот класс
                } catch (Exception e) {
                    // Здесь кода не будет
                }
                dialog.dismiss(); // закрываем дмалоговое окно
                // обрабатываем нажатие кнопки -- конец
            }
        });


        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show(); // показываем диологове окно


        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });

        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, ActivityLevel2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });

        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
        };


        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images[numLeft]);
        text_left.setText(array.texts[numLeft]);

        numRight = random.nextInt(10);

        // Цикл с предусловием, проверяем равенство чисел - начало
        while (numLeft==numRight) {
            numRight = random.nextInt(10);
        }
        //--

        img_right.setImageResource(array.images[numRight]);
        text_right.setText(array.texts[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 15) {
                            count = count + 1;
                        }

                        for (int i = 0; i < 15; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }
                        }

                        for (int i = 0; i < 14; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }

                    if (count == 15) {
                        dialogEnd.show();
                        // ВЫХОД ИЗ УРОВНИ
                    } else {
                    numLeft = random.nextInt(10);
                    img_left.setImageResource(array.images[numLeft]);
                    img_left.startAnimation(a);
                    text_left.setText(array.texts[numLeft]);

                    numRight = random.nextInt(10);

                    while (numLeft == numRight) {
                        numRight = random.nextInt(10);
                    }

                    img_right.setImageResource(array.images[numRight]);
                    img_right.startAnimation(a);
                    text_right.setText(array.texts[numRight]);

                    img_right.setEnabled(true);
                }
            }
                return true;
        }
        });


        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 15) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 15; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }
                        }
                        for (int i = 0; i < 14; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 15) {
                        dialogEnd.show();
                        // ВЫХОД ИЗ УРОВНИ
                    } else {
                            numLeft = random.nextInt(10);
                            img_left.setImageResource(array.images[numLeft]);
                            img_left.startAnimation(a);
                            text_left.setText(array.texts[numLeft]);

                            numRight = random.nextInt(10);

                            while (numLeft == numRight) {
                                numRight = random.nextInt(10);
                            }
                            img_right.setImageResource(array.images[numRight]);
                            img_right.startAnimation(a);
                            text_right.setText(array.texts[numRight]);

                            img_left.setEnabled(true);
                        }
                    }
                return true;
            }
        });
    }
        @Override
        public void onBackPressed() {
            try{
                Intent intent = new Intent(Level1.this, GameLevels.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
            }
        }
    }