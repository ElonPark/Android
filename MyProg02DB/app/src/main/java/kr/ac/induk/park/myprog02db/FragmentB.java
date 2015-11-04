package kr.ac.induk.park.myprog02db;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FragmentB extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseTest02Activity dt = new DatabaseTest02Activity();
      //  dt.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.database_test02, container, false);
    }
}