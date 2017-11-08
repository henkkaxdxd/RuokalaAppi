package ruokala.app;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;
import java.util.UUID;

public class BeaconService extends Application {

    private BeaconManager beaconManager;
    private String bmajori;

    private static final BeaconRegion allBeaconsRegion = new BeaconRegion("Beacons with default Estimote UUID",
            UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion region, List<Beacon> list) {

                Beacon beaconM = list.get(0);
                bmajori = String.valueOf(beaconM.getMajor());
                Log.i("pls", "majori: " + bmajori);

                showNotification("Sup brah","(logged in)");
            }

            @Override
            public void onExitedRegion(BeaconRegion region) {
                Log.i("pls", "poistunut alueelta");

                showNotification("Bye brah","(banned)");
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(allBeaconsRegion);
                Log.i("pls", "start monitoring ");
            }
        });
    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        /*
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
                */
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, RuokalistaActivity.class), 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(contentIntent) //pendingIntent
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}