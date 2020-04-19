package com.enterprise.version1.ejercicioactividadesysensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class FlashActivity extends AppCompatActivity {

    private Button regresar;
    private Button encender;
    private Button apagar;

    private Camera camera;
    private Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        regresar = findViewById(R.id.btnregresar2);
        encender = findViewById(R.id.btnencender);
        apagar = findViewById(R.id.btnapagar);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        encender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();

                if (Build.VERSION.SDK_INT>=26){
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                }else {
                vibrator.vibrate(2000);
                }

                encender.setEnabled(false);
                apagar.setEnabled(true);
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camera.stopPreview();
                apagar.setEnabled(false);
                encender.setEnabled(true);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        camera.stopPreview();
        camera.release();
    }
}
