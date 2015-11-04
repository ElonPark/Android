package kr.ac.induk.park.myprog02db;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class DatabaseTest01Activity extends Activity {
    dbHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_test01);
        helper = new dbHelper(this);
        try{
            db = helper.getWritableDatabase();

        }catch (SQLiteException e){
            db =helper.getReadableDatabase();
        }

        edit_name = (EditText) findViewById(R.id.editText);
        edit_tel = (EditText) findViewById(R.id.editText2);
        tv = (TextView) findViewById(R.id.textView3);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =  edit_name.getText().toString();
                String tel = edit_tel.getText().toString();

                db.execSQL("INSERT INTO contact VALUES (null, ' " +name
                        +"', '" + tel +"');");
            }
        });

        findViewById(R.id.listBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                Cursor cursor;
                cursor = db.rawQuery(
                        "SELECT name, tel FROM contact WHERE name='" + name + "';", null);
                while (cursor.moveToNext()) {
                    String tel = cursor.getString(1);
                    tv.setText(tel);
                }
            }
        });
    }
}

