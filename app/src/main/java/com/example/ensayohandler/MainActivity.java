package com.example.ensayohandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void Mensajito(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mensajito("Hola desde onCreate");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Mensajito("Handlerr!!!");
            }
        },7000);
    }
}
