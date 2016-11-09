package com.example.siddhantverma.assignment41;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.ArrayList;
import java.util.UUID;

public class DetailActivity extends AppCompatActivity{
    private static final String EXTRA_task_ID =
            "task_id";

    private ViewPager mViewPager;
    private ArrayList<Listitem> mtask;

    public static Intent newIntent(Context packageContext, UUID taskId) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(EXTRA_task_ID, taskId);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_task_ID);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mtask = taskactivity.get(this).getAllTasks();


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Listitem list= mtask.get(position);
                //return taskfragment.newInstance(list.getTaskid());
           return taskfragment.newInstance(list.getTaskid());
            }

            @Override
            public int getCount() {
                return mtask.size();
            }
        });

        for (int i = 0; i < mtask.size(); i++) {
            if (mtask.get(i).getTaskid().equals(taskId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    }


