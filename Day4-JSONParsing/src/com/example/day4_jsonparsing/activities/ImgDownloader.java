
package com.example.day4_jsonparsing.activities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.day4_jsonparsing.model.ImgCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImgDownloader extends AsyncTask< Integer, Void, Bitmap >
{
    ImgCache mCache = ImgCache.getInstance();

    ImageView mImageView;

    Context mContext;

    private boolean isIconOnly;

    private static final String imgUrlStartFull = "http://2.cdn.bravefrontier.gumi.sg/content/unit/img/unit_ills_full_";

    private static final String imgUrlStartIcon = "http://2.cdn.bravefrontier.gumi.sg/content/unit/img/unit_ills_thum_";

    private static final String imgUrlEnd = ".png";

    public ImgDownloader( ImageView view, Context context, boolean iconOnly )
    {
        mImageView = view;
        mContext = context;
        isIconOnly = iconOnly;
    }

    private Bitmap getImage( Integer id )
    {
        String imgUrlStart;
        if( isIconOnly )
        {
            imgUrlStart = imgUrlStartIcon;
        }
        else
        {
            imgUrlStart = imgUrlStartFull;
        }
        String url = imgUrlStart + id + imgUrlEnd;
        Bitmap cached = mCache.get( url );
        if( cached != null )
        {
            return cached;
        }
        try
        {
            byte[] bitmapBytes = getUrlBytes( url );
            Bitmap image = BitmapFactory.decodeByteArray( bitmapBytes, 0, bitmapBytes.length );
            mCache.put( url, image );
            return image;
        }
        catch( IOException e )
        {
            Log.e( "JSONParsing", "Error downloading image", e );
        }
        return null;
    }

    @Override
    protected Bitmap doInBackground( Integer... params )
    {
        Log.d( "JSONParsing", "downloading from: " + params[0] );
        return getImage( params[0] );
    }

    @Override
    protected void onPostExecute( Bitmap result )
    {
        mImageView.setImageBitmap( result );
    }

    private byte[] getUrlBytes( String urlSpec ) throws IOException
    {
        URL url = new URL( urlSpec );
        HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if( connection.getResponseCode() != HttpURLConnection.HTTP_OK )
            {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while( ( bytesRead = in.read( buffer ) ) > 0 )
            {
                out.write( buffer, 0, bytesRead );
            }
            out.close();
            byte[] output = out.toByteArray();
            return output;
        }
        finally
        {
            connection.disconnect();
        }
    }
}