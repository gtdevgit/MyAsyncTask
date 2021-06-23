package com.example.myasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBarCircle;
    ProgressBar progressBarHorizontal;
    MyAsyncTask myTask;

    MyAsyncTask.Listeners myAsyncTaskListener_A;
    MyAsyncTask.Listeners myAsyncTaskListener_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAsync();
            }
        });

        progressBarCircle = findViewById(R.id.progressBarCircle);
        progressBarHorizontal = findViewById(R.id.progressBar);

        myAsyncTaskListener_A = new MyAsyncTask.Listeners() {
            @Override
            public void onPreExecute() {
                updateUiBeforeTask();
            }

            @Override
            public void doInBackground() {

            }

            @Override
            public void onPostExecute(Long taskDuration) {
                updateUiAfterTask(taskDuration);
            }
        };

    }

    private void goAsync(){
        new MyAsyncTask(myAsyncTaskListener_A, 500).execute();
        new MyAsyncTask(myAsyncTaskListener_A, 10000).execute();
        new MyAsyncTask(myAsyncTaskListener_A, 3000).execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myTask != null) {
            myTask.cancel(true);
        }
    }

    private void updateUiBeforeTask(){
        progressBarCircle.setVisibility(View.VISIBLE);
        progressBarHorizontal.setVisibility(View.VISIBLE);
    }

    private void updateUiAfterTask(Long taskDuration){
        progressBarCircle.setVisibility(View.GONE);
        progressBarHorizontal.setVisibility(View.GONE);
        Date date = new Date(taskDuration);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
        String formatted = df.format(date);
        Toast.makeText(this, "Terminé en " + formatted , Toast.LENGTH_SHORT).show();
    }
}