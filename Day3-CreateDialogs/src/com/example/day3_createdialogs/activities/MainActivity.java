
package com.example.day3_createdialogs.activities;

import com.example.day3_createdialogs.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    Button mButton1;

    Button mButton2;

    Button mButton3;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mButton1 = ( Button ) findViewById( R.id.button1 );
        mButton1.setOnClickListener( new OnClickListener()
        {
            @Override
            public void onClick( View v )
            {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( MainActivity.this );
                dialogBuilder.setTitle( "Are you hungry?" );
                dialogBuilder.setPositiveButton( "Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        Toast.makeText( MainActivity.this, "You are hungry.", Toast.LENGTH_SHORT ).show();
                    }
                } );
                dialogBuilder.setNeutralButton( "Maybe", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        Toast.makeText( MainActivity.this, "You may be hungry.", Toast.LENGTH_SHORT ).show();
                    }
                } );
                dialogBuilder.setNegativeButton( "No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        Toast.makeText( MainActivity.this, "You are not hungry.", Toast.LENGTH_SHORT ).show();
                    }
                } );
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            }
        } );
        mButton2 = ( Button ) findViewById( R.id.button2 );
        mButton3 = ( Button ) findViewById( R.id.button3 );
    }
}
