package kr.ac.induk.park.myprog01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 203a21 on 2015-08-07.
 */
public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 2;

    public dbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contact ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, tel TEXT);");
        for (int i = 0; i < 20; i++) {
            db.execSQL("INSERT INTO contact VALUES (null, " + "'This is a sample data " + i
                    + "'" + ", '010-9801-100" + i + "');");
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }

}
