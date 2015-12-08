
package com.example.day3_createdialogs.activities;

import java.util.ArrayList;

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
        mButton2.setOnClickListener( new OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                final ArrayList< Integer > choice = new ArrayList< Integer >();
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( MainActivity.this );
                dialogBuilder.setTitle( "Choose your starter Pokemon:" );
                dialogBuilder.setSingleChoiceItems( R.array.starter_pokemon, -1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        if( choice.isEmpty() )
                        {
                            choice.add( which );
                            return;
                        }
                        choice.set( 0, which );
                    }
                } );
                dialogBuilder.setPositiveButton( "I choose you!", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        String text;
                        if( choice.isEmpty() )
                        {
                            text = "You did not choose any Pokemon!";
                        }
                        else
                        {
                            String[] choices = getResources().getStringArray( R.array.starter_pokemon );
                            text = choices[choice.get( 0 )] + ", I choose you!";
                        }
                        Toast.makeText( MainActivity.this, text, Toast.LENGTH_SHORT ).show();
                    }
                } );
                dialogBuilder.setNegativeButton( "I cannot choose.", null );
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
            }
        } );
        mButton3 = ( Button ) findViewById( R.id.button3 );
    }
}
