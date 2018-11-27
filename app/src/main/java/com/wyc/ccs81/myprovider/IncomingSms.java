package com.wyc.ccs81.myprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.wyc.ccs81.myprovider.MainActivity;

public class IncomingSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.d("RRBBsms", phoneNumber);
                    Log.d("RRBBsms", message);
                    try {
                        if (senderNum.equals("Ccs-Tanuj")) {
                            MainActivity Sms = new MainActivity();
                            Sms.recivedSms(message);
                        }
                    } catch (Exception e) {
                    }

                }
            }

        } catch (Exception e) {

        }
    }

}