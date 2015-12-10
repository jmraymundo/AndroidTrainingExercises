
package com.example.day4_jsonparsing.activities;

import com.example.day4_jsonparsing.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class UnitImageActivity extends Activity
{
    private ImageView imgView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_image );
        imgView = ( ImageView ) findViewById( R.id.imageView );
        ImgDownloader downloader = new ImgDownloader( imgView, UnitImageActivity.this, false );
        downloader.execute( getIntent().getIntExtra( UnitActivity.UNIT_ID, 0 ) );
    }
}
