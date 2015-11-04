package kr.ac.induk.park.accelmetertest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mAcclometer;

    MyView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyView(this);
        setContentView(view);

        mSensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);
        mAcclometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAcclometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected  void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            view.setX_accel(event.values[0]);
            view.setY_accel(event.values[1]);
            view.setZ_accel(event.values[2]);
            view.invalidate();
        }

    }
}
