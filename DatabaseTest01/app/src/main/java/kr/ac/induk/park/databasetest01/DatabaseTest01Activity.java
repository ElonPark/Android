package kr.ac.induk.park.databasetest01;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 203a21 on 2015-08-07.
 */
  class dbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE contact ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"name TEXT, tel TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}

public class DatabaseTest01Activity extends Activity {

    dbHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new dbHelper(this);
        try{
            db = helper.getWritableDatabase();

        }catch (SQLiteException e){
            db =helper.getReadableDatabase();
        }

        edit_name = (EditText) findViewById(R.id.editText);
        edit_tel = (EditText) findViewById(R.id.editText2);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =  edit_name.getText().toString();
                String tel = edit_tel.getText().toString();

                db.execSQL("INSERT INTO contact VALUES (null, ' " +name
                +"', '" + tel +"');");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                Cursor cursor;
                cursor = db.rawQuery(
                        "SELECT name, tel FROM contact WHERE name='" + name + "';", null);
                while (cursor.moveToNext()) {
                    String tel = cursor.getString(1);
                    edit_tel.setText(tel);
                }
            }
        });
    }
}

