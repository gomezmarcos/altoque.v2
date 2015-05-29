package com.agea.altoque.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 27/05/15.
 */
public class GalleryAdapter extends FragmentPagerAdapter {

    List<String> images;
    List<ImageFragment> fragments;

    public GalleryAdapter(FragmentManager fm)
    {
        super(fm);
        fragments = new ArrayList<ImageFragment>();
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.isEmpty() || fragments.size() < images.size() ){
            ImageFragment fragment = new ImageFragment();
            fragment.setImageUrl(images.get(position));
            fragments.add(fragment);
            return fragment;
        }

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return images==null? 0 : images.size();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
