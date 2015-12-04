
package com.example.day2_createcontextmenu.activities;

import com.example.day2_createcontextmenu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity
{
    TextView tvColor, tvSize;

    final int MENU_COLOR_RED = 1;

    final int MENU_COLOR_GREEN = 2;

    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;

    final int MENU_SIZE_26 = 5;

    final int MENU_SIZE_30 = 6;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        tvColor = ( TextView ) findViewById( R.id.tvColor );
        tvSize = ( TextView ) findViewById( R.id.tvSize );

        // context menu should be created for tvColor and tvSize
        registerForContextMenu( tvColor );
        registerForContextMenu( tvSize );

    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, ContextMenuInfo menuInfo )
    {
        // TODO Auto-generated method stub
        switch( v.getId() )
        {
            case R.id.tvColor:
                menu.add( 0, MENU_COLOR_RED, 0, "Red" );
                menu.add( 0, MENU_COLOR_GREEN, 0, "Green" );
                menu.add( 0, MENU_COLOR_BLUE, 0, "Blue" );
                break;
            case R.id.tvSize:
                menu.add( 0, MENU_SIZE_22, 0, "22" );
                menu.add( 0, MENU_SIZE_26, 0, "26" );
                menu.add( 0, MENU_SIZE_30, 0, "30" );
                break;
        }
    }
}
