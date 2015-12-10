
package com.example.day3_displayingbitmapsefficiently.activities;

import com.example.day3_displayingbitmapsefficiently.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends Activity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fragment );
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById( R.id.fragmentContainer );

        if( fragment == null )
        {
            fragment = createFragment();
            manager.beginTransaction().add( R.id.fragmentContainer, fragment ).commit();
        }
    }

    protected abstract Fragment createFragment();
}
