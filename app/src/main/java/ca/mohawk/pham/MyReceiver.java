package ca.mohawk.pham;

import static android.provider.Telephony.Sms.Intents.getMessagesFromIntent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public static String MSG = "MSG";
    @Override
    public void onReceive(Context context, Intent intent) {
        //get message
        SmsMessage[] messages = getMessagesFromIntent(intent);
        String sender = messages[0].getOriginatingAddress();
        String msg = messages[0].getOriginatingAddress() + "\n" + messages[0].getMessageBody();
        //send intent with message
        Intent myIntent = new Intent(context,
                MainActivity.class);
        myIntent.putExtra(MSG, msg);

        // Set the New Activity Flags
        myIntent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK );

        context.startActivity(myIntent);
    }
}
