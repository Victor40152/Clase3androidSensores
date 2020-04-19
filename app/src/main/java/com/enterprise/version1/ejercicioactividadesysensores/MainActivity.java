package com.enterprise.version1.ejercicioactividadesysensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button acelerometro;
    private Button flash;
    private Button cerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acelerometro = findViewById(R.id.btnacelerometro);
        flash = findViewById(R.id.btnflash);
        cerrar = findViewById(R.id.btncerrar);

        acelerometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AcelerometroActivity.class);
                startActivity(intent);
            }
        });

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlashActivity.class);
                startActivity(intent);
        }

        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });
    }
}
