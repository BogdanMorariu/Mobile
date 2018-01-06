package com.pf.bogdan.pharmacy;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bogdan on 1/6/2018.
 */

public class ButtonListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.cancel(intent.getExtras().getInt("id"));
    }
}
