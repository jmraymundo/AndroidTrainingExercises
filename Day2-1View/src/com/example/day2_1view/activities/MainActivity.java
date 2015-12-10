
package com.example.day2_1view.activities;

import com.example.day2_1view.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{

    TextView tvOut;

    Button btnOk;

    Button btnCancel;

    private static final String TAG = "myLogs";

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // find View-elements
        Log.d( TAG, "find View-elements" );
        tvOut = ( TextView ) findViewById( R.id.tvOut );
        btnOk = ( Button ) findViewById( R.id.btnOk );
        btnCancel = ( Button ) findViewById( R.id.btnCancel );

        // assign listener to buttons
        Log.d( TAG, "assign lstener to buttons" );
        btnOk.setOnClickListener( this );
        btnCancel.setOnClickListener( this );
    }

    public void onClick( View v )
    {
        // define the button that invoked the listener by id
        Log.d( TAG, "define the button that invoked the listener by id" );
        switch( v.getId() )
        {
            case R.id.btnOk:
                // ОК button
                Log.d( TAG, "ОК button" );
                tvOut.setText( "ОК button was clicked" );
                Toast.makeText( this, "ОК button was clicked", Toast.LENGTH_LONG ).show();
                break;
            case R.id.btnCancel:
                // Cancel button
                Log.d( TAG, "Cancel button" );
                tvOut.setText( "Cancel button was clicked" );
                Toast.makeText( this, "Cancel button was clicked", Toast.LENGTH_LONG ).show();
                break;
        }
    }
}