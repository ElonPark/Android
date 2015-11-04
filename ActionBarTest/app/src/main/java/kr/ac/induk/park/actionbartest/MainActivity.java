package kr.ac.induk.park.actionbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        switch (item.getItemId()){
            case R.id.action_refresh:
                Toast.makeText(this,"refresh", Toast.LENGTH_SHORT).show();
            case R.id.action_search:
                Toast.makeText(this,"search", Toast.LENGTH_SHORT).show();
            case R.id.action_settings:
                Toast.makeText(this,"settings", Toast.LENGTH_SHORT).show();
                default:
                    return super.onOptionsItemSelected(item);
        }


    }
}
