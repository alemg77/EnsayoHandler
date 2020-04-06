package com.example.ensayohandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void Mensajito(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Aqui creo un hilo de ejecucion que solamente muestra una tostada.
         */
        final Handler unHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                // Recupero el mensaje que le llega
                Bundle datos = msg.getData();
                // Este mensaje se podra ver aunque el programa este en otra pantalla
                Mensajito(datos.getString("KeyMensaje"));
            }
        };

        /*
        Esta parte se encarga de esperar 2 segundos a modo de presentacion
            luego crea un segundo hilo de ejecucion que iniciara dentro de 4 segundos.
            Al segundo hilo de ejecucion le envia un texto para indicarle que debe hacer
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crea el mensaje a enviar al segundo hilo
                Message msg = new Message();

                // Crea un paquete de datos
                Bundle bundle = new Bundle();

                // Carga en el paquete de datos un dato.
                bundle.putString("KeyMensaje","Esto se ejecuta en un hilo aparte");

                // Asocio coloco el paquete de datos dentro del mensaje
                msg.setData(bundle);

                // Pido que cree el hilo dentro de 4 segundos e indico que mensaje debe enviar al hilo
                unHandler.sendMessageDelayed(msg, 2000);

                // Me paso a otra pantalla para verificar que el Handler es otro hilo que se ejecuta aun estando en otra pantalla
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                // Inicio la otra actividad
                startActivity(intent);
            }
        },2000);
    }
}
