package com.enterprise.version1.ejercicioactividadesysensores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcelerometroActivity extends AppCompatActivity {

    private Button regresar;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private int contador = 0;

    private View izquierdo;
    private View derecho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

        regresar = findViewById(R.id.btnregresar2);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        izquierdo = findViewById(R.id.vizquierdo);
        derecho = findViewById(R.id.vderecho);
        if (sensor == null){

            finish();
        }
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float inclinacion = event.values[0];

                if (inclinacion < -5 && contador == 0) {
                    contador++;
                    derecho.setBackgroundColor(Color.rgb(255, 190, 51));
                    izquierdo.setBackgroundColor(Color.rgb(51, 128, 255));

                } else if (inclinacion < 5 && contador == 1) {
                    contador++;
                    derecho.setBackgroundColor(Color.rgb(255, 190, 51));
                    izquierdo.setBackgroundColor(Color.rgb(51, 128, 255));

                } else if (inclinacion == 0) {
                    izquierdo.setBackgroundColor(Color.rgb(255, 255, 255));
                    derecho.setBackgroundColor(Color.rgb(255, 255, 255));
                }
                if (contador == 2 ){
                    contador = 0;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };
       start();
    }
    private void start(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected  void onPause(){
        stop();
        super.onPause();
    }
}
