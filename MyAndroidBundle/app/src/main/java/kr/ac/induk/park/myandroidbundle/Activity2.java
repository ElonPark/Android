package kr.ac.induk.park.myandroidbundle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.Date;

/**
 * Created by 203a21 on 2015-08-05.
 */
public class Activity2 extends Activity {
    TextView label2;
    Button btnCallActivity1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        label2 = (TextView) findViewById(R.id.label2);
        btnCallActivity1 = (Button) findViewById(R.id.btnCallActivity1);
        btnCallActivity1.setOnClickListener(new Clicker1());
        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();


        String str1 = myBundle.getString("myString1");
        double dob1 = myBundle.getDouble("myDouble1");
        int[] arr1 = myBundle.getIntArray("myIntArray1");


        String strArr = "{ ";
        int sumIntValues = 0;
        for (int i = 0; i < arr1.length; i++) {
            sumIntValues += arr1[i];
            strArr += Integer.toString(arr1[i]) + " ";
        }
        strArr += " }";


        label2.setText("Activity2   (receiving...) \n\n" +
                "myString1:   " + str1 + "\n" +
                "myDouble1:   " + Double.toString(dob1) + "\n" +
                "myIntArray1: " + strArr);


        double someNumber = sumIntValues + dob1;
        myBundle.putString("myReturnedString1", "Adios Android");
        myBundle.putDouble("myReturnedDouble1", someNumber);
        myBundle.putString("myCurrentTime", new Date().toLocaleString());
        myLocalIntent.putExtras(myBundle);

        setResult(Activity.RESULT_OK, myLocalIntent);

    }//onCreate

    private class Clicker1 implements OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

}


