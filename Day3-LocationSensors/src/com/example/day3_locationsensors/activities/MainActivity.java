
package com.example.day3_locationsensors.activities;

import com.example.day3_locationsensors.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener
{
    private GoogleMap mGoogleMap;

    private Location mCurrentLocation;

    private LocationManager mLocationManager;

    @Override
    public void onMapReady( GoogleMap map )
    {
        mGoogleMap = map;
        mGoogleMap.setMyLocationEnabled( true );
        updateUI();
    }

    private void updateUI()
    {
        if( mCurrentLocation == null || mGoogleMap == null )
        {
            return;
        }
        LatLng latLngPos = new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() );
        mGoogleMap.clear();
        mGoogleMap.addMarker( new MarkerOptions().position( latLngPos ) ).setTitle( "You are here!" );
        mGoogleMap.moveCamera( CameraUpdateFactory.newLatLng( latLngPos ) );
    }

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable( MainActivity.this );
        if( status != ConnectionResult.SUCCESS )
        {
            displayErrorDialog( status );
        }
        else
        {
            getMap();
        }
        mLocationManager = ( LocationManager ) getSystemService( LOCATION_SERVICE );
        String gpsProvider = LocationManager.GPS_PROVIDER;
        String networkProvider = LocationManager.NETWORK_PROVIDER;
        Location lastKnownLocation = mLocationManager.getLastKnownLocation( gpsProvider );
        if( lastKnownLocation == null )
        {
            lastKnownLocation = mLocationManager.getLastKnownLocation( networkProvider );
        }
        if( lastKnownLocation != null )
        {
            mCurrentLocation = lastKnownLocation;
            updateUI();
        }
        mLocationManager.requestLocationUpdates( gpsProvider, 0, 0, this );
        mLocationManager.requestLocationUpdates( networkProvider, 0, 0, this );
    }

    private void getMap()
    {
        SupportMapFragment fm = ( SupportMapFragment ) getSupportFragmentManager().findFragmentById( R.id.map );
        fm.getMapAsync( this );
    }

    private void displayErrorDialog( int status )
    {
        int requestCode = 10;
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog( status, this, requestCode );
        dialog.show();
    }

    @Override
    public void onStatusChanged( String provider, int status, Bundle extras )
    {
        Toast.makeText( MainActivity.this, provider + " status changed!", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onProviderEnabled( String provider )
    {
        Toast.makeText( MainActivity.this, provider + " is enabled!", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onProviderDisabled( String provider )
    {
        Toast.makeText( MainActivity.this, provider + " is disabled!", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onLocationChanged( Location location )
    {
        mCurrentLocation = location;
        updateUI();
    }
}
