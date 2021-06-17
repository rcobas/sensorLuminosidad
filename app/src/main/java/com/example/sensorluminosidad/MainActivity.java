package com.example.sensorluminosidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private View layoutRaiz;
    private SensorManager sensorManager;
    private Sensor sensorLight;
    private SensorEventListener sensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutRaiz = (View) findViewById(R.id.layoutRaiz);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (sensorLight == null) {
            Toast.makeText(this, "El dispositivo no tiene sensor de luz", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float lux = event.values[0];
        getSupportActionBar().setTitle("Luminosidad: " + lux + " lx");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorLight, sensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}