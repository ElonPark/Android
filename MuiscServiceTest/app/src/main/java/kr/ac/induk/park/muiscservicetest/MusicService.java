package kr.ac.induk.park.muiscservicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 203a21 on 2015-08-10.
 */
public class MusicService extends Service {
    private static final String TAG ="MusicService";
    MediaPlayer player;

   public IBinder onBind (Intent intent){
       return null;
   }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");


        player = MediaPlayer.create(this, R.raw.old_pop);
        player.setLooping(false);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Music Service가 중지 되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy()");
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Music Service가 시작 되었습니다.", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
