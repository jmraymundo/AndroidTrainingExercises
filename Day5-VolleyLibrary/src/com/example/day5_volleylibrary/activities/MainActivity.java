package com.example.day5_volleylibrary.activities;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.day5_volleylibrary.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private TextView txtDisplay;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        txtDisplay = ( TextView ) findViewById( R.id.txtDisplay );

        RequestQueue queue = Volley.newRequestQueue( this );
        String url = "http://api.androidhive.info/contacts/";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest( Request.Method.GET, url, null,
                new Response.Listener< JSONObject >()
                {
                    @Override
                    public void onResponse( JSONObject response )
                    {
                        Toast.makeText( MainActivity.this, "Response received!", Toast.LENGTH_SHORT ).show();
                        // TODO Auto-generated method stub
                        txtDisplay.setText( "Response => " + response.toString() );
                        findViewById( R.id.progressBar1 ).setVisibility( View.GONE );
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse( VolleyError error )
                    {
                        Toast.makeText( MainActivity.this, "Error response received! " + error.getMessage(),
                                Toast.LENGTH_SHORT ).show();
                    }
                } );
        queue.add( jsObjRequest );

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
}
