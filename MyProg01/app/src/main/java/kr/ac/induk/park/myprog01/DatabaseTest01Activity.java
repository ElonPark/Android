package kr.ac.induk.park.myprog01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


public class DatabaseTest01Activity extends Activity {

    dbHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_test01);
        setTitle("연락처 입력");
        helper = new dbHelper(this);
        try {
            db = helper.getWritableDatabase();

        } catch (SQLiteException e) {
            db = helper.getReadableDatabase();
        }

        edit_name = (EditText) findViewById(R.id.editText);
        edit_tel = (EditText) findViewById(R.id.editText2);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String tel = edit_tel.getText().toString();

                db.execSQL("INSERT INTO contact VALUES (null, ' " + name
                        + "', '" + tel + "');");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callSecond = new Intent(DatabaseTest01Activity.this, DatabaseTest02Activity.class);
                final int reqCode = 1;
                startActivityForResult(callSecond, reqCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

