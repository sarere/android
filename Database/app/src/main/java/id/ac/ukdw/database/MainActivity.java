package id.ac.ukdw.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtUsername;
    private EditText editTxtPassword;
    private Button btnLogin;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("database",MODE_PRIVATE);
        if(!sp.getBoolean("isLogin",false)) {
            setContentView(R.layout.activity_main);

            editTxtUsername = (EditText) findViewById(R.id.editTxtUsername);
            editTxtPassword = (EditText) findViewById(R.id.editTxtPassword);
            btnLogin = (Button) findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        } else {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void login(){
        if(editTxtUsername.getText().toString().equals("sarere") && editTxtPassword.getText().toString().equals("rere")) {
            Intent intent = new Intent(this, HomeActivity.class);
            SharedPreferences.Editor spEdit = sp.edit();
            spEdit.putBoolean("isLogin", true);

            spEdit.commit();

            startActivity(intent);
            finish();
        }
    }
}
