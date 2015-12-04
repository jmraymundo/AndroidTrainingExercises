
package com.example.day2_1view.activities;

import com.example.day2_1view.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{

    TextView tvOut;

    Button btnOk;

    Button btnCancel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // find View-elements
        tvOut = ( TextView ) findViewById( R.id.tvOut );
        btnOk = ( Button ) findViewById( R.id.btnOk );
        btnOk.setOnClickListener( oclBtnOk );
        btnCancel = ( Button ) findViewById( R.id.btnCancel );
        btnCancel.setOnClickListener( oclBtnCancel );
    }

    OnClickListener oclBtnOk = new OnClickListener()
    {
        @Override
        public void onClick( View v )
        {
            tvOut.setText( "Button OK clicked" );
        }
    };

    OnClickListener oclBtnCancel = new OnClickListener()
    {
        @Override
        public void onClick( View v )
        {
            // Change text of TextView (tvOut)
            tvOut.setText( "Button Cancel clicked" );
        }
    };
}