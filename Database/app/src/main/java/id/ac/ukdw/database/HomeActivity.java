package id.ac.ukdw.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mahasiswa on 9/25/17.
 */

public class HomeActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnSimpan;
    private TextView txtHasil;
    private EditText txtNama;
    private EditText txtNim;

    private SharedPreferences sp;

    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        txtHasil = (TextView)findViewById(R.id.txtHasil);
        txtNama = (EditText)findViewById(R.id.txtNama);
        txtNim = (EditText)findViewById(R.id.txtNim);

        sp = getSharedPreferences("database",MODE_PRIVATE);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void logout(){
        Intent intent = new Intent(this,MainActivity.class);

        SharedPreferences.Editor spEdit = sp.edit();
        spEdit.putBoolean("isLogin",false);

        spEdit.commit();

        startActivity(intent);
        finish();
    }

    private void saveData(){
        db.execSQL("INSERT INTO mahasiswa (nim, nama) VALUES('" +
                txtNim.getText().toString() + "','" +
                txtNama.getText().toString() + "')"
        );

        txtNama.setText("");
        txtNim.setText("");

        showData();
    }

    private void showData(){
        Cursor cursor = db.rawQuery("SELECT nim, nama FROM mahasiswa",null);
        txtHasil.setText("");
        for(int i=0 ; i<cursor.getCount() ; i++){
            cursor.moveToPosition(i);
            txtHasil.setText(txtHasil.getText().toString() +
            " " + cursor.getString(0) + " - " + cursor.getString(1) + "\n");
        }
    }
}
