package kr.ac.induk.park.myintent2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView label1;
    EditText text1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            label1 = (TextView) findViewById(R.id.textView);
            text1 = (EditText) findViewById(R.id.editText);

            btn = (Button) findViewById(R.id.button);
            btn.setOnClickListener(new ClickHandler());
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private class ClickHandler implements View.OnClickListener {
        public void onClick(View v) {
            try {
                String myData = text1.getText().toString();
                Intent Act2 = new Intent(Intent.ACTION_PICK,
                        Uri.parse(myData));
                startActivityForResult(Act2, 222);
            } catch (Exception e) {
                label1.setText(e.getMessage());
            }

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case (222): {
                    if (resultCode == Activity.RESULT_OK) {
                        String selectedContact = data.getDataString();
                        label1.setText(selectedContact.toString());
                        Intent Act3 = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedContact));
                        startActivity(Act3);

                    } else {
                        label1.setText("Selection CANCELLED " + requestCode + " " + resultCode);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
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
