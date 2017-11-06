package id.ac.ukdw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahasiswa on 9/25/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"database",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mahasiswa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nim TEXT," +
                "nama TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
