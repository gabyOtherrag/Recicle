package com.example.recicle;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

public class ConfirmacaoCadastro extends ContextWrapper {

    private static final String EDMTV_CHANNEL_ID = "com.example.recicle.COMDEV";
    private static final String EDMTV_CHANNEL_NAME = "COMDEV channel";
    private NotificationManager manager;

    public ConfirmacaoCadastro(Context base) {
        super(base);

        Canais();
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void Canais(){

        NotificationChannel channel = new NotificationChannel
                (EDMTV_CHANNEL_ID, EDMTV_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(Color.BLUE);
        channel.setLockscreenVisibility(android.app.Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);

    }

    public NotificationManager getManager(){
        if (manager == null) {
            manager = (NotificationManager) getSystemService(ConfirmacaoCadastro.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public NotificationCompat.Builder builder (String titulo, String texto){

        Intent intent = new Intent(ConfirmacaoCadastro.this, MainActivity.class);
        PendingIntent pdIntent = PendingIntent.getActivity
                (ConfirmacaoCadastro.this, 1, intent, PendingIntent.FLAG_MUTABLE);

        return new NotificationCompat.Builder(getApplicationContext(), EDMTV_CHANNEL_ID)
                .setContentText(texto)
                .setContentTitle(titulo)
                .setSmallIcon(R.drawable.ic_baseline_recycling_24)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true).setContentIntent(pdIntent);
    }

}
