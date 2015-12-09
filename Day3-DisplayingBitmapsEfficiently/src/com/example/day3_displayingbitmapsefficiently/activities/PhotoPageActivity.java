
package com.example.day3_displayingbitmapsefficiently.activities;

import com.example.day3_displayingbitmapsefficiently.fragment.PhotoPageFragment;

import android.app.Fragment;

public class PhotoPageActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new PhotoPageFragment();
    }
}
