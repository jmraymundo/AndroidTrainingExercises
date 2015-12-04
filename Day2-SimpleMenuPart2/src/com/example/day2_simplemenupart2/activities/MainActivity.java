
package com.example.day2_simplemenupart2.activities;

import com.example.day2_simplemenupart2.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity
{

    // Screen elements
    TextView tv;

    CheckBox chb;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        // find the elements
        tv = ( TextView ) findViewById( R.id.textView );
        chb = ( CheckBox ) findViewById( R.id.chbExtMenu );

    }

    // menu creation
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.mymenu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    // menu update
    @Override
    public boolean onPrepareOptionsMenu( Menu menu )
    {
        // TODO Auto-generated method stub
        // menu items with group ID = 1 are visible if CheckBox is checked
        menu.setGroupVisible( R.id.group1, chb.isChecked() );
        return super.onPrepareOptionsMenu( menu );
    }

    // process clicks
    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();

        // print the info about pressed menu item
        sb.append( "Item Menu" );
        sb.append( "\r\n groupId: " + String.valueOf( item.getGroupId() ) );
        sb.append( "\r\n itemId: " + String.valueOf( item.getItemId() ) );
        sb.append( "\r\n order: " + String.valueOf( item.getOrder() ) );
        sb.append( "\r\n title: " + item.getTitle() );
        tv.setText( sb.toString() );

        return super.onOptionsItemSelected( item );
    }
}