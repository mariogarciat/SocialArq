package co.edu.udea.compumovil.socialchallenge;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private String content = "";

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        content = intent.getExtras().getString("content");
        Intent notiIntent = new Intent(context, NotificationService.class);
        notiIntent.putExtra("content", content);
        context.startService(notiIntent);


    }
}
