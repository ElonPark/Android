package kr.ac.induk.park.framelayout;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // int cl=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ibtn = (ImageButton) findViewById(R.id.imageButton);
        ibtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
               /* cl++;
                int a=cl%2;
                if(a==0) {
                    image.setVisibility(View.INVISIBLE);
                }else {
                    image.setVisibility(View.VISIBLE);
                }*/

                if (image.getVisibility() == View.VISIBLE) {
                    image.setVisibility(View.INVISIBLE);
                } else {
                    image.setVisibility(View.VISIBLE);
                }
                Toast.makeText(getApplicationContext(), "버튼이 눌러졌습니다.", Toast.LENGTH_SHORT).show();

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
