package kr.ac.induk.park.intentlisttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 203a00 on 2015-08-05.
 */
public class SecondActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String[] girlsGeneration = {"Taeyon", "Tiffany", "Sunny", "Hyoyeon", "Yuri",
                "Sooyoung", "Yoona", "Seohyun", "Crystal"};
        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, girlsGeneration);

        final ListView thelistView = (ListView) findViewById(R.id.list_item);

        thelistView.setAdapter(theAdapter);

        thelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String myfriend = String.valueOf(thelistView.getItemAtPosition(position)) + " is my friend";

                Intent myIntent = getIntent();
                myIntent.putExtra("userName", myfriend);
                setResult(RESULT_OK, myIntent);
                Log.i("Tag", "before called finish()");
                finish();
                Log.i("Tag", "After called finish()");
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("LifeCycle", "onStart() 호출");


    }

    public void onResume() {
        super.onResume();
        Log.i("LifeCycle", "onResume() 호출");
    }

    public void onPause() {
        super.onPause();
        Log.i("LifeCycle", "onPause() 호출");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle", "onResume() 호출");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle", "onDestroy() 호출");
    }

}
