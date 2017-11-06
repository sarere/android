package id.ac.ukdw.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        int rotation = display.getRotation();

        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
            setContentView(R.layout.activity_main_landscape);
        } else {
            setContentView(R.layout.fragment_one);
        }
    }
}
