package kr.ac.induk.park.providertest01;

import android.database.Cursor;

import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String projection[] = new String[]{
                Contacts.People.NAME,
                Contacts.People.NUMBER
        };


    Cursor c = this.getContentResolver().query(Contacts.Phones.CONTENT_URI, projection, null, null, Contacts.People.NAME + " ASC");

    c.moveToFirst();


    int nameColumn = c.getColumnIndex( Contacts.People.NAME);
    int phoneColumn = c.getColumnIndex( Contacts.People.NUMBER);
    do

    {
        String name = c.getString(nameColumn);
        String phoneNumber = c.getString(phoneColumn);

        Toast.makeText(this, name + ":" + phoneNumber, Toast.LENGTH_SHORT).show();
    }

    while(c.moveToFirst());

}
}
