package id.ac.ukdw.kamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button btnFoto;
    private ImageView imgFoto;
    private int REQUEST_CAMERA = 12;
    private String pathFoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFoto = (Button)findViewById(R.id.btnFoto);
        imgFoto = (ImageView)findViewById(R.id.imgFoto);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeFoto();
            }
        });
    }

    private File createFile(){
        //proses pembuatan nama file
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "PROGMOB_" + timestamp;

        //directory untuk menyimpan file
        File storage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Progmob");
        storage.mkdirs();

        //membuat file
        File image = null;
        try {
            image = File.createTempFile(fileName,".jpg",storage);
        }catch (Exception e){

        }
        return image;
    }

    private void takeFoto(){
        File image = createFile();
        if(image != null){
            pathFoto = Uri.fromFile(image).getPath();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //implisit intent
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
            startActivityForResult(intent,REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAMERA){
            Bitmap bitmap = BitmapFactory.decodeFile(pathFoto);
            imgFoto.setImageBitmap(bitmap);
        }
    }
}
