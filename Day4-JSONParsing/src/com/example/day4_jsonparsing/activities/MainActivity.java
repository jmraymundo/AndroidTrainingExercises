
package com.example.day4_jsonparsing.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.day4_jsonparsing.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getJSONString();

    }

    private String getJSONString()
    {
        String output = null;
        try
        {
            InputStream is = getAssets().open( "units_info.json" );
            InputStreamReader isReader = new InputStreamReader( is );
            JsonReader reader = new JsonReader( isReader );
            List< Unit > topTen = new ArrayList< Unit >();
            reader.beginObject();
            while( reader.hasNext() )
            {
                String unitId = reader.nextName();
                String unitName = null;
                String unitLordDmgRange = null;
                reader.beginObject();
                while( reader.hasNext() )
                {
                    String propertyName = reader.nextName();
                    if( propertyName.equals( "name" ) )
                    {
                        unitName = reader.nextString();
                    }
                    else if( propertyName.equals( "lord damage range" ) )
                    {
                        unitLordDmgRange = reader.nextString();
                    }
                    else
                    {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                topTen.add( new Unit( unitName, unitId, unitLordDmgRange ) );
            }
            reader.close();
            for( Unit unit : topTen )
            {
                Log.d( "JSONParsing", ( topTen.indexOf( unit ) + 1 ) + " " + unit.toString() );
            }
        }
        catch( Exception e )
        {
            Log.e( "JSONParsing", "Exception! " + e );
        }
        return output;
    }

    private class Unit
    {
        String mId;

        String mName;

        String mLordDmg;

        public Unit( String id, String name, String lordDmg )
        {
            mId = id;
            mName = name;
            mLordDmg = lordDmg;
        }

        @Override
        public String toString()
        {
            return "Unit name: " + mName + " Unit ID: " + mId + " Ave. damage: " + mLordDmg;
        }
    }
}
