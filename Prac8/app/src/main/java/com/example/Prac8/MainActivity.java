package com.example.Prac8;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSequential = findViewById(R.id.buttonSequential);
        Button buttonParallel = findViewById(R.id.buttonParallel);

        buttonSequential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Запускаем последовательно 3 задачи
                SequentialTask sequentialTask = new SequentialTask();
                sequentialTask.execute();
            }
        });

        buttonParallel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Запускаем параллельно 2 задачи
                ParallelTask parallelTask1 = new ParallelTask();
                ParallelTask parallelTask2 = new ParallelTask();

                parallelTask1.execute();
                parallelTask2.execute();
            }
        });
    }

    private class SequentialTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Здесь выполняем первую задачу последовательно
            publishProgress(); // Показываем промежуточный прогресс
            // Выполнение первой задачи

            publishProgress(); // Показываем промежуточный прогресс
            // Выполнение второй задачи

            publishProgress(); // Показываем промежуточный прогресс
            // Выполнение третьей задачи

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // Обновляем UI для отображения прогресса
            Toast.makeText(MainActivity.this, "Последовательное выполнение", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Выполняется после завершения всех задач
            Toast.makeText(MainActivity.this, "Все задачи завершены", Toast.LENGTH_SHORT).show();
        }
    }

    public void toImageDraw(View view) {
        Intent intent = new Intent(this, ImageShow.class);
        startActivity(intent);
    }


    private class ParallelTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Запускаем два таймера для имитации параллельного выполнения задач
            TimerTask timerTask1 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Задача 1 выполнена", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };

            TimerTask timerTask2 = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Задача 2 выполнена", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };

            Timer timer1 = new Timer();
            Timer timer2 = new Timer();

            // Задержка в 2 секунды для имитации времени выполнения задачи
            timer1.schedule(timerTask1, 2000);
            timer2.schedule(timerTask2, 1000);

            return null;
        }
    }
}
