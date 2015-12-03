
package com.example.day1.activities;

import com.example.day1.adapters.MobileArrayAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListMobileActivity extends ListActivity
{

    static final String[] MOBILE_OS = new String[] { "Andaria", "Deimos", "Zedus", "Zenia" };

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setListAdapter( new MobileArrayAdapter( this, MOBILE_OS ) );

    }

    @Override
    protected void onListItemClick( ListView l, View v, int position, long id )
    {

        // get selected items
        String selectedValue = ( String ) getListAdapter().getItem( position );
        Toast.makeText( this, selectedValue, Toast.LENGTH_SHORT ).show();

    }

}
