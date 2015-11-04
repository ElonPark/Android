package kr.ac.induk.park.intentdemo1;

import android.app.Activity;
import android.content.DialogInterface;
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

    EditText editText;
    Button btnCall;
    TextView label1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText =(EditText) findViewById(R.id.editText);
        btnCall = (Button) findViewById(R.id.button);

        btnCall.setOnClickListener(new ClickHandler());

    }
    class ClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            try {
                //String myData = editText.getText().toString();
                //String myData = "http://youtube.com";
                String myData = "content://contacts/people/";
                Intent myActivity2 = new Intent(Intent.ACTION_PICK, Uri.parse(myData));
                startActivityForResult(myActivity2, 222);
            }
            catch (Exception e ) {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data);
        try{
            switch (requestCode) {
                case(222) : {
                    if (resultCode == Activity.RESULT_OK){
                        String selectedContact = data.getDataString();
                        label1.setText(selectedContact.toString());
                        Intent myAct3 = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedContact));
                        startActivity(myAct3);
                    }
                    else {
                        label1.setText("Canceled");

                    }
                    break;
                }

            } //switch
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_LONG);
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
