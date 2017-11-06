package id.ac.ukdw.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Komponen Layout
    private EditText txtChat;
    private Button btnKirim;
    private TextView txtHasil;

    //Komponen Database Firebase
    private FirebaseDatabase db;
    private DatabaseReference dbChat; //nyimpan data chatting
    private List<String> listChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi Database Firebase
        db = FirebaseDatabase.getInstance();
        //inisialisasi DB CHAT (Kolom)
        dbChat = db.getReference("chat");

        //addValueEventListener butuh satu parameter ValueEventListener
        dbChat.addValueEventListener(new ValueEventListener() {
            //Ketika data chat pada server berubah
            //data dari firebase berada di dataSnapshot
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //kembalian dari dataSnapshot adalah object maka harus di-casting
                listChat = (List<String>) dataSnapshot.getValue();
                if(listChat == null){
                    listChat = new ArrayList<String>();
                } else{
                    String tmp = "";
                    for (int i=0 ; i < listChat.size(); i++){
                        tmp += listChat.get(i).toString()+"\n";
                    }
                    txtHasil.setText(tmp);
                }
            }

            //ketika error mis:koneksi error
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtChat = (EditText) findViewById(R.id.txtChat);
        txtHasil = (TextView) findViewById(R.id.txtHasil);
        btnKirim = (Button) findViewById(R.id.btnKirim);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listChat.add("rere : " + txtChat.getText().toString());
                dbChat.setValue(listChat);
                txtHasil.setText("");
            }
        });
    }
}
