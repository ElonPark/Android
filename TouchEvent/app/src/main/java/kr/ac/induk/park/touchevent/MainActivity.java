package kr.ac.induk.park.touchevent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    protected class MyView extends View {
        int x = 100, y =100;
        String str;
        public MyView(Context context){
            super(context);
            setBackgroundColor(Color.YELLOW);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            Path path = new Path();
            paint.setStrokeWidth(10f);
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(40);
            paint.setAntiAlias(true);
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(x, y, x + 50, y + 50, paint);
            canvas.drawPath(path, paint);
            canvas.drawText("액션의 종류: " + str, 0, 50, paint);


        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x= (int) event.getX();
            y=(int) event.getY();

            if(event.getAction() == MotionEvent.ACTION_DOWN)
                str = "ACTION_DOWN";
            if(event.getAction() == MotionEvent.ACTION_MOVE)
                str = "ACTION_MOVE";
            if(event.getAction() == MotionEvent.ACTION_UP)
                str = "ACTION_UP";
            invalidate();
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView my = new MyView(this);
        setContentView(my);

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
