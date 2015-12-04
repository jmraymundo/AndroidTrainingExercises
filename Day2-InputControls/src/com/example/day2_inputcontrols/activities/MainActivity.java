
package com.example.day2_inputcontrols.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.day2_inputcontrols.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
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
        mSpinnerButton.setOnClickListener( new PrivateOnClickListener() );
        mDatePickerButton = ( Button ) findViewById( R.id.showDatePickerButton );
        mDatePickerButton.setOnClickListener( new PrivateOnClickListener() );
        mTimePickerButton = ( Button ) findViewById( R.id.showTimePickerButton );
        mTimePickerButton.setOnClickListener( new PrivateOnClickListener() );
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

    private class PrivateOnClickListener implements android.view.View.OnClickListener
    {
        @Override
        public void onClick( View v )
        {
            int id = v.getId();
            switch( id )
            {
                case R.id.showSpinnerButton:
                    showSpinner();
                    break;
                case R.id.showDatePickerButton:
                    showDatePicker();
                    break;
                case R.id.showTimePickerButton:
                    showTimePicker();
                    break;
                default:
                    return;
            }
        }
    }

    private void showSpinner()
    {
        final Spinner spinner = new Spinner( this );
        String[] options = { "Bulbasaur", "Charmander", "Eevee", "Pikachu", "Squirtle" };
        spinner.setPrompt( "Choose your starter Pokemon" );
        ArrayAdapter< String > spinnerAdapter = new ArrayAdapter< String >( this,
                android.R.layout.simple_spinner_dropdown_item, options );
        spinner.setAdapter( spinnerAdapter );
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( MainActivity.this );
        dialogBuilder.setView( spinner );
        dialogBuilder.setPositiveButton( "OK", new OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialog, int which )
            {
                showText( "You have chosen " + spinner.getSelectedItem() );
            }
        } );
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showDatePicker()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get( Calendar.YEAR );
        int month = calendar.get( Calendar.MONTH );
        int day = calendar.get( Calendar.DAY_OF_MONTH );
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, new OnDateSetListener()
        {
            @Override
            public void onDateSet( DatePicker view, int year, int month, int day )
            {
                GregorianCalendar setCalendar = new GregorianCalendar( year, month, day );
                Date setDate = setCalendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat( "MMM dd, yyyy" );
                showText( "You have set the date to " + format.format( setDate ) );
            }
        }, year, month, day );
        datePickerDialog.show();
    }

    private void showTimePicker()
    {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get( Calendar.HOUR_OF_DAY );
        int minute = calendar.get( Calendar.MINUTE );
        TimePickerDialog timePickerDialog = new TimePickerDialog( this, new OnTimeSetListener()
        {
            @Override
            public void onTimeSet( TimePicker view, int hourOfDay, int minute )
            {
                int year = calendar.get( Calendar.YEAR );
                int month = calendar.get( Calendar.MONTH );
                int day = calendar.get( Calendar.DAY_OF_MONTH );
                GregorianCalendar setCalendar = new GregorianCalendar( year, month, day, hourOfDay, minute );
                Date setDate = setCalendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat( "hh:mm aa" );
                showText( "You have set the time to " + format.format( setDate ) );
            }
        }, hour, minute, false );
        timePickerDialog.show();
    }

    private void showText( String text )
    {
        Toast.makeText( getApplicationContext(), text, Toast.LENGTH_SHORT ).show();
    }
}
