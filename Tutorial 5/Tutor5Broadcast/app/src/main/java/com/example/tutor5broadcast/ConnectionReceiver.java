package com.example.tutor5broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals("IntentA")) {
            Toast.makeText(context, "Explicit : Button Click ", Toast.LENGTH_SHORT).show();
        }

        if(action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            Toast.makeText(context, "Implicit : Connectivity Changed", Toast.LENGTH_SHORT).show();
        }
     }
}
