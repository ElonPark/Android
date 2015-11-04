package kr.ac.induk.park.intentdemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 203a00 on 2015-08-05.
 */
public class SecondActivity extends Activity {
    TextView textView;
    EditText editText;
    Button theButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        textView = (TextView) findViewById(R.id.second_activity_text);
        editText = (EditText) findViewById(R.id.yourname);
        theButton = (Button) findViewById(R.id.call_main_activity);

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();

        String str1 = myBundle.getString("myName");

        textView.setText(str1);

        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = editText.getText().toString();

                Intent myIntent = getIntent();
                Bundle myBundle = myIntent.getExtras();

                myBundle.putString("userName", data);
                myIntent.putExtras(myBundle);

                setResult(Activity.RESULT_OK, myIntent);

                finish();
            }
        });

    }

}
