package kr.ac.induk.park.boundservicetest1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by 203a21 on 2015-08-10.
 */
public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private  final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        LocalService getService(){
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }
}
