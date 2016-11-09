package com.example.siddhantverma.assignment41;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddhant Verma on 09-Nov-16.
 */
public class PagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ArrayList<Listitem> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate
                (savedInstanceState);
        setContentView
                (R.layout.pager_layout);


    }
}
