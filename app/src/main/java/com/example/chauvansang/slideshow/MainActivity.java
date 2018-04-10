package com.example.chauvansang.slideshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageButton btnPrevious,btnNext;
    ImageView imgImage;
    CheckBox chkAutoLoad;
    int currentPosition=-1;
    ArrayList<String> albums;
    TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextImage();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPreviousImage();
            }
        });
        chkAutoLoad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    btnPrevious.setEnabled(false);
                    btnNext.setEnabled(false);
                    autoLoadImage();
                }
                else
                {
                    btnNext.setEnabled(true);
                    btnPrevious.setEnabled(true);
                    if(timerTask!=null)
                        timerTask.cancel();
                }
            }
        });
    }

    private void autoLoadImage() {
        timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentPosition++;
                        if(currentPosition==albums.size())
                        {
                            currentPosition=0;
                        }
                        ImageTask imageTask=new ImageTask();
                        imageTask.execute(albums.get(currentPosition));
                    }
                });
            }
        };
        Timer timer=new Timer();
        timer.schedule(timerTask,0,5000);
    }

    private void loadPreviousImage() {
        currentPosition--;
        if(currentPosition==-1)
        {
            currentPosition=albums.size()-1;
        }
        ImageTask imageTask=new ImageTask();
        imageTask.execute(albums.get(currentPosition));
    }

    private void loadNextImage() {
        currentPosition++;
        if(currentPosition==albums.size())
        {
            currentPosition=0;
        }
        ImageTask imageTask=new ImageTask();
        imageTask.execute(albums.get(currentPosition));
    }

    private void addControls() {
        imgImage= (ImageView) findViewById(R.id.imgImage);
        chkAutoLoad= (CheckBox) findViewById(R.id.chkAutoLoad);
        btnPrevious= (ImageButton) findViewById(R.id.btnPrevious);
        btnNext= (ImageButton) findViewById(R.id.btnNext);

        //Load data in resources
        albums=new ArrayList<>();
        albums.addAll(Arrays.asList(getResources().getStringArray(R.array.link)));

        //Load first image in albums
        currentPosition=0;
        ImageTask imageTask=new ImageTask();
        imageTask.execute(albums.get(currentPosition));
    }
    class ImageTask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgImage.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String link=strings[0];
            Bitmap bitmap=null;
            try
            {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(link).getContent());
            }catch (Exception ex)
            {
                Log.e("ERROR",ex.toString());
            }
            return bitmap;
        }
    }
}
