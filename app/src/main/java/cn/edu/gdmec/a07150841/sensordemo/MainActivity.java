package cn.edu.gdmec.a07150841.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView tAcc,tOri,tLight;
    private SensorManager sensorManager;
    private Sensor sAccelerometer,sOrientation,sLight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAcc= (TextView) findViewById(R.id.accelerometer);
        tOri= (TextView) findViewById(R.id.orientation);
        tLight= (TextView) findViewById(R.id.light);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        sAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sOrientation=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sLight=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[SensorManager.DATA_X];
        float y=sensorEvent.values[SensorManager.DATA_Y];
        float z=sensorEvent.values[SensorManager.DATA_Z];
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOri.setText("方位:"+x+","+y+","+z);
        }
        else if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAcc.setText("加速度:"+x+","+y+","+z);
        }else if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线:"+sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

