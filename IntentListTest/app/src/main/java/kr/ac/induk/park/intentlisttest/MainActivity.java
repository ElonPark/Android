package kr.ac.induk.park.intentlisttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button theButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.main_activity_text);
        editText = (EditText) findViewById(R.id.myname);
        theButton = (Button) findViewById(R.id.call_second_activity);

        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callSecond = new Intent(MainActivity.this, SecondActivity.class);

                Bundle myData = new Bundle();
                String name = editText.getText().toString();
                myData.putString("myName", name);

                callSecond.putExtras(myData);
                final int reqCode = 1;
                startActivityForResult(callSecond, reqCode);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView tv = (TextView) findViewById(R.id.main_activity_fromName);
        String fromName = data.getStringExtra("userName");
        tv.setText(fromName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
