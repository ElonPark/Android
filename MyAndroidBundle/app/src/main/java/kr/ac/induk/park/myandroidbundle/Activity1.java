package kr.ac.induk.park.myandroidbundle;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class Activity1 extends Activity {
    TextView label1;
    TextView label1Returned;
    Button btnCallActivity2;
    private final int IPC_ID = 1122;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.
                    activity_main);
            label1 = (TextView) findViewById(R.id.label1);
            label1Returned = (TextView) findViewById(R.id.label1Returned);
            btnCallActivity2 = (Button) findViewById(R.id.btnCallActivity2);
            btnCallActivity2.setOnClickListener(new Clicker1());
            label1.setText("Activity1   (sending...) \n\n"
                    + "myString1:  Hello Android" + "\n"
                    + "myDouble1:  3.141592     " + "\n"
                    + "myIntArray: {1 2 3} ");
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }// onCreate

    private class Clicker1 implements OnClickListener {
        public void onClick(View v) {
            try {
                Intent myIntentA1A2 = new Intent(Activity1.this,
                        Activity2.class);

                Bundle myData = new Bundle();
                myData.putString("myString1", "Hello Android");
                myData.putDouble("myDouble1", 3.141592);
                int[] myLittleArray = {1, 2, 3};
                myData.putIntArray("myIntArray1", myLittleArray);

                myIntentA1A2.putExtras(myData);

                startActivityForResult(myIntentA1A2, IPC_ID);
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case IPC_ID: {
                    if (resultCode == Activity.RESULT_OK) {

                        Bundle myReturnedData = data.getExtras();
                        String myReturnedString1 = myReturnedData
                                .getString("myReturnedString1");
                        Double myReturnedDouble1 = myReturnedData
                                .getDouble("myReturnedDouble1");
                        String myReturnedString2 = myReturnedData
                                .getString("myCurrentTime");

                        label1Returned.setText(myReturnedString1 + "\n"
                                + Double.toString(myReturnedDouble1) + "\n"
                                + myReturnedString2);
                    } else {

                        label1.setText("Selection CANCELLED!");
                    }
                    break;
                }
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

}