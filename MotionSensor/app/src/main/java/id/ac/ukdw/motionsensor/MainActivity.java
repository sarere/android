package id.ac.ukdw.motionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtX = (TextView)findViewById(R.id.txtX);
        txtY= (TextView)findViewById(R.id.txtY);
        txtZ = (TextView)findViewById(R.id.txtZ);

        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //angka dari sensornya
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                txtX.setText("X: " + sensorEvent.values[0]);
                txtY.setText("Y: " + sensorEvent.values[1]);
                txtZ.setText("Z: " + sensorEvent.values[2]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, sensor, sm.SENSOR_DELAY_NORMAL);
    }
}
