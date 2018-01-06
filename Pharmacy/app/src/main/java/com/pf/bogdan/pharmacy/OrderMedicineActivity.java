package com.pf.bogdan.pharmacy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RemoteViews;
import android.widget.Toast;

public class OrderMedicineActivity extends AppCompatActivity {

    NumberPicker numberPicker;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_medicine);
        createNotification();
        numberPicker = findViewById(R.id.quantityPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
    }

    private void createNotification(){
        Context context = getApplicationContext();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(),R.layout.notification);

        remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.notification);
        remoteViews.setTextViewText(R.id.notif_title,"Pharmacy-Online misses you :(");

        notification_id = (int) System.currentTimeMillis();
        Intent buttonIntent = new Intent("button_clicked");
        buttonIntent.putExtra("id",notification_id);

        PendingIntent pButtonIntent = PendingIntent.getBroadcast(context,77,buttonIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.notif_button,pButtonIntent);
    }

    public void orderMedicine(View view){
        Intent notificationIntent = new Intent(getApplicationContext(),OrderMedicineActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,notificationIntent,0);
        builder = new NotificationCompat.Builder(getApplicationContext(),"M_CH_ID");
        //builder.setSmallIcon(R.mipmap.notification).setAutoCancel(true)
        //        .setCustomBigContentView(remoteViews).setContentIntent(pendingIntent);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.notification)
                .setContentTitle("Pharmacy-Online")
                .setContentText("Pharmacy-Online misses you :(");
        notificationManager.notify(notification_id,builder.build());

        EditText nameText = findViewById(R.id.orderMedicineName);
        EditText producerText = findViewById(R.id.orderMedicineProducer);
        if(nameText.getText().toString().equals("")){
            Toast.makeText(this,"Plsease enter a Medicine Name",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tipitza@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Medicine");
        intent.putExtra(Intent.EXTRA_TEXT,"Your order for " + numberPicker.getValue() + " " +
                nameText.getText().toString() + "'s by " + producerText.getText().toString() + " has been placed");
        startActivity(Intent.createChooser(intent, "Send mail with"));
    }
}
