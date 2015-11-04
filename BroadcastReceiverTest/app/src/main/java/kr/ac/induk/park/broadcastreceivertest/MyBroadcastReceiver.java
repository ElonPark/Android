package kr.ac.induk.park.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by 203a21 on 2015-08-10.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str ="";
        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for(int i= 0; i < msgs.length; i++){
                msgs[i] =SmsMessage.createFromPdu((byte[])pdus[i]);
                str += "Sms from "+ msgs[i].getOriginatingAddress();
                str += " : ";
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT ).show();
        }
    }
}
