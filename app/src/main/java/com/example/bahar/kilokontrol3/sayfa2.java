package com.example.bahar.kilokontrol3;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class sayfa2 extends AppCompatActivity implements SensorEventListener , StepListener {
    StepDetector simpleStepDetector;
    SensorManager sensorManager;
    Sensor accel;
    ToggleButton ackapa;
    TextView TvSteps;
    public static final String step = "stepKey";
    String LOG_TYPE="Adim Sayar";

    int durumkolonu;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor ;



    private static final String TEXT_NUM_STEPS = "Atılan Adım Sayısı: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayfa2);

        sharedPreferences=getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        numSteps=sharedPreferences.getInt("AdimSayisi",0);
        Log.e(LOG_TYPE,numSteps+" Baslangic");


        Toast.makeText(getApplicationContext(),"Kayıt Edilen Adim Sayisi : "+numSteps,Toast.LENGTH_SHORT).show();


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tvsteps);
        ackapa = (ToggleButton) findViewById(R.id.ackapa);
        if(durumkolonu==0)
        {
            ackapa.setChecked(false);
        }
        else
        {
            ackapa.setChecked(true);
        }

    }
    public void  onClick (View v) {

        boolean on = ((ToggleButton) v).isChecked();

        if (on)
        {
            Toast.makeText(getApplication(),"açık",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(sayfa2.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            String n  = TvSteps.getText().toString();
        }
        else
            {

            Toast.makeText(getApplication(),"kapalı",Toast.LENGTH_SHORT).show();
            sensorManager.unregisterListener(sayfa2.this);

        }



    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;

        Toast.makeText(getApplicationContext(),"Adim Sayisi : "+numSteps,Toast.LENGTH_SHORT).show();
        Log.e(LOG_TYPE,numSteps+"");

        editor.putInt("AdimSayisi", numSteps);
        editor.commit();
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

}

