
package com.example.day4_jsonparsing.model;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImgCache extends LruCache< String, Bitmap >
{
    private static ImgCache instance;

    public static ImgCache getInstance()
    {
        if( instance == null )
        {
            instance = new ImgCache();
        }
        return instance;
    }

    private ImgCache()
    {
        super( 100 );
    }

}
