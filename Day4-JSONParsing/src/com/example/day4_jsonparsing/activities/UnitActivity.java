
package com.example.day4_jsonparsing.activities;

import com.example.day4_jsonparsing.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class UnitActivity extends Activity
{
    private int id;

    private String name;

    private int hp;

    private int atk;

    private int def;

    private int rec;

    private TextView unitIdView;

    private TextView unitNameView;

    private TextView unitHpView;

    private TextView unitAtkView;

    private TextView unitDefView;

    private TextView unitRecView;

    private ImageView unitImgView;

    protected static final String UNIT_ID = "UNIT_ID";

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_unit );
        Bundle extra = getIntent().getExtras();
        id = extra.getInt( MainActivity.UNIT_ID );
        name = extra.getString( MainActivity.UNIT_NAME );
        hp = extra.getInt( MainActivity.UNIT_HP );
        atk = extra.getInt( MainActivity.UNIT_ATK );
        def = extra.getInt( MainActivity.UNIT_DEF );
        rec = extra.getInt( MainActivity.UNIT_REC );
        unitImgView = ( ImageView ) findViewById( R.id.unitImage );
        unitImgView.setOnClickListener( new OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                Intent i = new Intent( UnitActivity.this, UnitImageActivity.class );
                i.putExtra( UNIT_ID, id );
                startActivity( i );
            }
        } );
        ImgDownloader downloader = new ImgDownloader( unitImgView, UnitActivity.this, true );
        downloader.execute( id );
        unitIdView = ( TextView ) findViewById( R.id.unitId );
        unitIdView.setText( "ID: " + id );
        unitNameView = ( TextView ) findViewById( R.id.unitName );
        unitNameView.setText( "Name: " + name );
        unitHpView = ( TextView ) findViewById( R.id.unitHp );
        unitHpView.setText( String.valueOf( hp ) );
        unitAtkView = ( TextView ) findViewById( R.id.unitAtk );
        unitAtkView.setText( String.valueOf( atk ) );
        unitDefView = ( TextView ) findViewById( R.id.unitDef );
        unitDefView.setText( String.valueOf( def ) );
        unitRecView = ( TextView ) findViewById( R.id.unitRec );
        unitRecView.setText( String.valueOf( rec ) );
    }
}
