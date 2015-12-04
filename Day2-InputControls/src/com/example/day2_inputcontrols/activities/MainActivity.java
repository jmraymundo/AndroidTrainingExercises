
package com.example.day2_inputcontrols.activities;

import com.example.day2_inputcontrols.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity
{
    Button mSpinnerButton;

    Button mDatePickerButton;

    Button mTimePickerButton;

    CheckBox mCheckBox;

    RadioButton mRadioButton1;

    RadioButton mRadioButton2;

    RadioGroup mRadioGroup;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mSpinnerButton = ( Button ) findViewById( R.id.showSpinnerButton );
        mDatePickerButton = ( Button ) findViewById( R.id.showDatePickerButton );
        mTimePickerButton = ( Button ) findViewById( R.id.showTimePickerButton );
        mCheckBox = ( CheckBox ) findViewById( R.id.checkBox );
        mCheckBox.setOnCheckedChangeListener( new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked )
            {
                String text = buttonView.getText() + " is ";
                if( isChecked )
                {
                    text = text.concat( "checked!" );
                }
                else
                {
                    text = text.concat( "unchecked!" );
                }
                showText( text );
            }
        } );
        mRadioButton1 = ( RadioButton ) findViewById( R.id.radioButton1 );
        mRadioButton1.setOnCheckedChangeListener( new PrivateOnCheckChangeListener() );
        mRadioButton2 = ( RadioButton ) findViewById( R.id.radioButton2 );
        mRadioButton2.setOnCheckedChangeListener( new PrivateOnCheckChangeListener() );
        mRadioGroup = ( RadioGroup ) findViewById( R.id.radioGroup );
    }

    private class PrivateOnCheckChangeListener implements OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged( CompoundButton buttonView, boolean isChecked )
        {
            if( isChecked )
            {
                String text = buttonView.getText() + " is selected!";
                showText( text );
            }
        }
    }

    private void showText( String text )
    {
        Toast.makeText( getApplicationContext(), text, Toast.LENGTH_SHORT ).show();
    }
}
