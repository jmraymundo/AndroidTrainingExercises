
package com.example.day3_displayingbitmapsefficiently.fragment;

import com.example.day3_displayingbitmapsefficiently.services.PollService;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class VisibleFragment extends Fragment
{
    private static final String TAG = "VisibleFragment";

    private BroadcastReceiver mOnShowNotification = new BroadcastReceiver()
    {
        @Override
        public void onReceive( Context context, Intent intent )
        {
            Log.i( TAG, "cancelling notification" );
            setResultCode( Activity.RESULT_CANCELED );
        }
    };

    @Override
    public void onPause()
    {
        super.onPause();
        getActivity().unregisterReceiver( mOnShowNotification );
    };

    @Override
    public void onResume()
    {
        super.onResume();
        IntentFilter filter = new IntentFilter( PollService.ACTION_SHOW_NOTIFICATION );
        getActivity().registerReceiver( mOnShowNotification, filter, PollService.PERMISSION_PRIVATE, null );
    }
}
