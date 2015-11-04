package kr.ac.induk.park.myviewexam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;


/**
 * Created by 203a21 on 2015-08-04.
 */
public class MyView extends View {
    int key;
    String str;

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        str = "" + keyCode;
        invalidate();
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawText("키코드 = " + str, 0, 20, paint);
    }




}
