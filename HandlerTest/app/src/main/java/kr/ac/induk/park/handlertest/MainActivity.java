package kr.ac.induk.park.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.annotation.WorkerThread;


public class MainActivity extends AppCompatActivity {

    class WorkerThread extends Thread{
        Handler handler;
        WorkerThread(Handler handler){
            this.handler = handler;
        }

        @Override
        public void run() {
            for(int i = 0; i <20; i++){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = i;
                handler.sendMessage(msg);
            }
        }
    }

    WorkerThread thread;
    TextView text;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        handler = new Handler(){
            public  void handleMessage(Message msg){
                if(msg.what ==1){
                    text.setText("카운터=" + msg.arg1);
                }
            }
        };
        thread = new WorkerThread(handler);
        thread.start();
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
