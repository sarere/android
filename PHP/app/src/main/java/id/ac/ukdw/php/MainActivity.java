package id.ac.ukdw.php;

import android.app.DownloadManager;
import android.media.MediaTimestamp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText txtAngka;
    private Button btnKirim;

    private String url = "http://lecturer.ukdw.ac.id/~adinugraha/";
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAngka = (EditText)findViewById(R.id.editText2);
        btnKirim = (Button)findViewById(R.id.button2);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendRequest(url+"keren_service.php","{\"angka\":"+txtAngka.getText().toString()+"}");
                } catch (Exception e){

                }
            }
        });
    }

    private void sendRequest(String url,String data){
        OkHttpClient client = new OkHttpClient();
        RequestBody reqBody = RequestBody.create(JSON, data);
        Request req = new Request.Builder()
                .url(url)
                .post(reqBody)
                .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
