
package com.example.photogallery.activity;

import com.example.photogallery.R;
import com.example.photogallery.fragment.PhotoGalleryFragment;
import com.example.photogallery.utils.FlickrFetcher;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

public class PhotoGalleryActivity extends SingleFragmentActivity
{
    private static final String TAG = "PhotoGalleryActivity";

    @Override
    protected Fragment createFragment()
    {
        return new PhotoGalleryFragment();
    }

    @Override
    protected void onNewIntent( Intent intent )
    {
        PhotoGalleryFragment fragment = ( PhotoGalleryFragment ) getFragmentManager()
                .findFragmentById( R.id.fragmentContainer );
        String query = null;
        if( Intent.ACTION_SEARCH.equals( intent.getAction() ) )
        {
            query = intent.getStringExtra( SearchManager.QUERY );
            Log.i( TAG, "Received a new query search: " + query );

            PreferenceManager.getDefaultSharedPreferences( this ).edit()
                    .putString( FlickrFetcher.PREF_SEARCH_QUERY, query ).commit();
        }
        fragment.updateItems();
    }
}
