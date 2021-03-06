package org.thoughtcrime.securesms.notifications;

/**
 * Created by rendra on 05/11/18.
 */

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import org.thoughtcrime.securesms.BuildConfig;
import org.thoughtcrime.securesms.R;

import java.util.Arrays;

public class NotificationChannels {

    public static String MESSAGES = "messages";
    public static String CALLS = "calls";
    public static String FAILURES = "failures";
    public static String APP_UPDATES = "app_updates";
    public static String BACKUPS = "backups";
    public static String LOCKED_STATUS = "locked_status";
    public static String OTHER = "other";

    /**
     * Ensures all of the notification channels are created. No harm in repeat calls. Call is safely
     * ignored for API < 26.
     */
    public static void create(@NonNull Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }

        NotificationChannel messages = new NotificationChannel(MESSAGES, context.getString(R.string.NotificationChannel_messages), NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel calls = new NotificationChannel(CALLS, context.getString(R.string.NotificationChannel_calls), NotificationManager.IMPORTANCE_LOW);
        NotificationChannel failures = new NotificationChannel(FAILURES, context.getString(R.string.NotificationChannel_failures), NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel backups = new NotificationChannel(BACKUPS, context.getString(R.string.NotificationChannel_backups), NotificationManager.IMPORTANCE_LOW);
        NotificationChannel lockedStatus = new NotificationChannel(LOCKED_STATUS, context.getString(R.string.NotificationChannel_locked_status), NotificationManager.IMPORTANCE_LOW);
        NotificationChannel other = new NotificationChannel(OTHER, context.getString(R.string.NotificationChannel_other), NotificationManager.IMPORTANCE_LOW);

        calls.setShowBadge(false);
        backups.setShowBadge(false);
        lockedStatus.setShowBadge(false);
        other.setShowBadge(false);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannels(Arrays.asList(messages, calls, failures, backups, lockedStatus, other));

        if (BuildConfig.PLAY_STORE_DISABLED) {
            NotificationChannel appUpdates = new NotificationChannel(APP_UPDATES, context.getString(R.string.NotificationChannel_app_updates), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(appUpdates);
        } else {
            notificationManager.deleteNotificationChannel(APP_UPDATES);
        }
    }
}