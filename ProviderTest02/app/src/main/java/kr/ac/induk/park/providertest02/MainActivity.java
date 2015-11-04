package kr.ac.induk.park.providertest02;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.Contacts.People.Phones;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText nameEdit;
    EditText phoneEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText) findViewById(R.id.editText);
        phoneEdit = (EditText) findViewById(R.id.editText2);
        Button read = (Button) findViewById(R.id.button);
        Button write = (Button) findViewById(R.id.button2);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();

                String name = nameEdit.getText().toString();
                String phone = phoneEdit.getText().toString();

                values.put(People.STARRED, 1);

                Uri uri = getContentResolver().insert(People.CONTENT_URI, values);

                Uri phoneUri = Uri.withAppendedPath(uri ,Phones.CONTENT_DIRECTORY);
                values.clear();
                values.put(Phones.TYPE,Phones.TYPE_MOBILE);
                values.put(People.NUMBER, phone);
                getContentResolver().insert(phoneUri, values);
            }
        });
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
