package kr.ac.induk.park.myintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button btb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
             textView = (TextView) findViewById(R.id.textView);
             editText = (EditText) findViewById(R.id.editText);
             btb = (Button) findViewById(R.id.button);
            btb.setOnClickListener(new ClickHandler());
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }


   private class ClickHandler implements OnClickListener {
       public void onClick(View v) {
           try {
               String myData = "tel:"+editText.getText().toString();
               Intent Act2 = new Intent(Intent.ACTION_DIAL,
                       Uri.parse(myData));
               startActivity(Act2);
           } catch (Exception e) {
               Toast.makeText(getBaseContext(), e.getMessage(),
                       Toast.LENGTH_LONG).show();
           }

       }
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
