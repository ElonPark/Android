package kr.co.company.preftest01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class PrefTest01Activity extends Activity {
    public static final String PREFS_NAME = "MyPrefs";
	TextView name;
	EditText value;
	String imageName;

    @Override
    protected void onCreate(Bundle state){
		super.onCreate(state);
		setContentView(R.layout.main);

		name = (TextView)findViewById(R.id.TextView01);
		value = (EditText)findViewById(R.id.EditText01);


		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		imageName = settings.getString("imageName", "");
		value.setText(imageName);
    }

    @Override
    protected void onStop(){
		super.onStop();

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

		SharedPreferences.Editor editor = settings.edit();
		imageName = value.getText().toString();
		editor.putString("imageName", imageName);

		editor.commit();
    }
}
