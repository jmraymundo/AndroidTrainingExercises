
package com.example.day4_jsonparsing.activities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.day4_jsonparsing.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity
{
    protected static final String UNIT_REC = "unitRec";

    protected static final String UNIT_DEF = "unitDef";

    protected static final String UNIT_ATK = "unitAtk";

    protected static final String UNIT_HP = "unitHp";

    protected static final String UNIT_ID = "unitId";

    protected static final String UNIT_NAME = "unitName";

    private ListView mListView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getJSONString();

    }

    private void getJSONString()
    {
        mListView = ( ListView ) findViewById( R.id.listView );
        try
        {
            InputStream is = getAssets().open( "units_info.json" );
            InputStreamReader isReader = new InputStreamReader( is );
            JsonReader reader = new JsonReader( isReader );
            List< Unit > unitList = new ArrayList< Unit >();
            reader.beginObject();
            while( reader.hasNext() )
            {
                int unitId = Integer.parseInt( reader.nextName() );
                String unitName = null;
                Stats stats = null;
                reader.beginObject();
                while( reader.hasNext() )
                {
                    String propertyName = reader.nextName();
                    if( propertyName.equals( "name" ) )
                    {
                        unitName = reader.nextString();
                    }
                    else if( propertyName.equals( "stats" ) )
                    {
                        int hp = 0;
                        int atk = 0;
                        int def = 0;
                        int rec = 0;
                        reader.beginObject();
                        while( reader.hasNext() )
                        {
                            String statsType = reader.nextName();
                            if( statsType.equals( "_base" ) )
                            {
                                reader.beginObject();
                                while( reader.hasNext() )
                                {
                                    String statsClass = reader.nextName();
                                    if( statsClass.equals( "hp" ) )
                                    {
                                        hp = reader.nextInt();
                                    }
                                    else if( statsClass.equals( "atk" ) )
                                    {
                                        atk = reader.nextInt();
                                    }
                                    else if( statsClass.equals( "def" ) )
                                    {
                                        def = reader.nextInt();
                                    }
                                    else if( statsClass.equals( "rec" ) )
                                    {
                                        rec = reader.nextInt();
                                    }
                                    else
                                    {
                                        reader.skipValue();
                                    }
                                }
                                reader.endObject();
                            }
                            else
                            {
                                reader.skipValue();
                            }
                        }
                        reader.endObject();
                        stats = new Stats( hp, atk, def, rec );
                    }
                    else
                    {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                unitList.add( new Unit( unitId, unitName, stats ) );
            }
            reader.close();
            mListView.setAdapter( new UnitAdapter( unitList ) );
        }
        catch( Exception e )
        {
            Log.e( "JSONParsing", "Exception! " + e );
        }
    }

    private class Unit
    {
        int id;

        String name;

        Stats baseStats;

        public Unit( int newId, String newName, Stats newStats )
        {
            id = newId;
            name = newName;
            baseStats = newStats;
        }

        @Override
        public String toString()
        {
            return "ID: " + id + " Name: " + name + " HP: " + baseStats.hp + " ATK: " + baseStats.atk + " DEF: "
                    + baseStats.def + " REC: " + baseStats.rec;
        }
    }

    private class Stats
    {
        int hp;

        int atk;

        int def;

        int rec;

        public Stats( int h, int a, int d, int r )
        {
            hp = h;
            atk = a;
            def = d;
            rec = r;
        }
    }

    private class UnitAdapter extends ArrayAdapter< Unit >
    {
        List< Unit > mItems;

        public UnitAdapter( List< Unit > items )
        {
            super( MainActivity.this, R.layout.activity_main, items );
            mItems = items;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent )
        {
            if( convertView == null )
            {
                convertView = getLayoutInflater().inflate( android.R.layout.simple_list_item_1, parent, false );
            }
            final Unit unit = mItems.get( position );
            TextView view = ( TextView ) convertView;
            view.setText( unit.name );
            view.setOnClickListener( new OnClickListener()
            {
                @Override
                public void onClick( View v )
                {
                    Intent i = new Intent( MainActivity.this, UnitActivity.class );
                    i.putExtra( UNIT_NAME, unit.name );
                    i.putExtra( UNIT_ID, unit.id );
                    i.putExtra( UNIT_HP, unit.baseStats.hp );
                    i.putExtra( UNIT_ATK, unit.baseStats.atk );
                    i.putExtra( UNIT_DEF, unit.baseStats.def );
                    i.putExtra( UNIT_REC, unit.baseStats.rec );
                    startActivity( i );
                }
            } );
            return convertView;
        }
    }
}
