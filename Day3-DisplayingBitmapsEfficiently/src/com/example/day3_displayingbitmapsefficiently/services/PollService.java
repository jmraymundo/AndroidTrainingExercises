
package com.example.day3_displayingbitmapsefficiently.services;

import java.util.ArrayList;

import com.example.day3_displayingbitmapsefficiently.R;
import com.example.day3_displayingbitmapsefficiently.activities.PhotoGalleryActivity;
import com.example.day3_displayingbitmapsefficiently.object.GalleryItem;
import com.example.day3_displayingbitmapsefficiently.utils.FlickrFetcher;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

public class PollService extends IntentService
{
    public static final String ACTION_SHOW_NOTIFICATION = "com.example.photogallery.services.SHOW_NOTIFICATION";

    public static final String EXTRA_NOTIFICATION = "NOTIFICATION";

    public static final String EXTRA_REQUEST_CODE = "REQUEST_CODE";

    public static final String PERMISSION_PRIVATE = "com.example.photogallery.PRIVATE";

    public static final String PREF_IS_ALARM_ON = "isAlarmOn";

    private static final int POLL_INTERVAL = 1000 * 5;

    private static final String TAG = "PollService";

    public PollService()
    {
        super( TAG );
    }

    public static boolean isServiceAlarmOn( Context context )
    {
        Intent i = new Intent( context, PollService.class );
        PendingIntent pi = PendingIntent.getService( context, 0, i, PendingIntent.FLAG_NO_CREATE );
        return pi != null;
    }

    public static void setAlarmService( Context context, boolean isOn )
    {
        Log.i( TAG, "Setting service! Setting to " + isOn );
        Intent i = new Intent( context, PollService.class );
        PendingIntent pi = PendingIntent.getService( context, 0, i, 0 );
        AlarmManager alarmManager = ( AlarmManager ) context.getSystemService( Context.ALARM_SERVICE );
        if( isOn )
        {
            alarmManager.setRepeating( AlarmManager.RTC, System.currentTimeMillis(), POLL_INTERVAL, pi );
        }
        else
        {
            alarmManager.cancel( pi );
            pi.cancel();
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
        prefs.edit().putBoolean( PREF_IS_ALARM_ON, isOn ).commit();
    }

    public void showBackgroundNotification( int requestCode, Notification notif )
    {
        Intent i = new Intent( ACTION_SHOW_NOTIFICATION );
        i.putExtra( EXTRA_REQUEST_CODE, requestCode );
        i.putExtra( EXTRA_NOTIFICATION, notif );

        sendOrderedBroadcast( i, PERMISSION_PRIVATE, null, null, Activity.RESULT_OK, null, null );
    }

    @Override
    protected void onHandleIntent( Intent intent )
    {
        ConnectivityManager cm = ( ConnectivityManager ) getSystemService( Context.CONNECTIVITY_SERVICE );
        @SuppressWarnings( "deprecation" )
        boolean isNetworkAvailable = cm.getBackgroundDataSetting() && cm.getActiveNetworkInfo() != null;

        if( !isNetworkAvailable )
        {
            Log.i( TAG, "Early termination in onHandleIntent" );
            return;

        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
        String query = prefs.getString( FlickrFetcher.PREF_SEARCH_QUERY, null );
        String lastResultId = prefs.getString( FlickrFetcher.PREF_LAST_RESULT_ID, null );

        ArrayList< GalleryItem > items;
        if( query != null )
        {
            items = new FlickrFetcher().search( query );
        }
        else
        {
            items = new FlickrFetcher().fetchItems();
        }
        if( items.size() == 0 )
        {
            return;
        }
        String resultId = items.get( 0 ).getId();
        if( !resultId.equals( lastResultId ) )
        {
            Log.i( TAG, "Got a new result: " + resultId );
            Resources r = getResources();
            PendingIntent pi = PendingIntent.getActivity( this, 0, new Intent( this, PhotoGalleryActivity.class ), 0 );

            Notification notification = new Builder( this ).setTicker( r.getString( R.string.new_pictures_title ) )
                    .setSmallIcon( android.R.drawable.ic_menu_report_image )
                    .setContentTitle( r.getString( R.string.new_pictures_title ) )
                    .setContentText( r.getString( R.string.new_pictures_text ) ).setContentIntent( pi )
                    .setAutoCancel( true ).build();

            showBackgroundNotification( 0, notification );
        }
        else
        {
            Log.i( TAG, "Got an old result: " + resultId );
        }

        prefs.edit().putString( FlickrFetcher.PREF_LAST_RESULT_ID, resultId ).commit();
    }
}
