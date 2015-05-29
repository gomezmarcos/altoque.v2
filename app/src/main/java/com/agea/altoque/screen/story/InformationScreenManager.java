package com.agea.altoque.screen.story;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.agea.altoque.R;
import com.agea.altoque.model.Information;
import com.agea.altoque.settings.SettingsFragment;
import com.agea.altoque.model.ViewHolderFactory;

import java.util.List;

/**
 * Created by marcos on 26/05/15.
 */
public class InformationScreenManager {

    private AppCompatActivity activity;
    private InformationAdapter adapter;
    private RecyclerView recyclerView;
    private SettingsFragment mSettingsFragment;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button filterImportant;
    private static SharedPreferences prefs;



    public InformationScreenManager(AppCompatActivity activity){
        this.activity = activity;
        this.activity=activity;

        ViewHolderFactory.FRAGMENT_MANAGER = this.activity.getSupportFragmentManager();
        loadSettings();
        initUI();
    }
       

    public void updateStories(List<Information> informations){

        //TODO validar repetidos.
        List<Information> listOldInformations = adapter.getInformations();

        if (listOldInformations != null)
        {
            for (Information info : informations)
            {
                listOldInformations.add(info);
            }
        }
        else
        {
            adapter.setInformations(informations);
        }

        adapter.notifyDataSetChanged();
    }


    private void initUI()
    {
        //Filter Button
        filterImportant = (Button) activity.findViewById(R.id.filterImportant);
        filterImportant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //TODO BUTTON FILTER, CHANGE IMAGE & FILTER LIST
            }
        });

        //LIST OF ITEMS
        recyclerView = (RecyclerView)activity.findViewById(R.id.stories);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new InformationAdapter(null);
        recyclerView.setAdapter(adapter);

        //TOOLBAR
        mToolbar = (Toolbar) activity.findViewById(R.id.mytoolbar);
        activity.setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ajustes_boton_normal);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //SETTINGS NAVIGATION DRAWER
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name)
        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                mSettingsFragment.onCloseSettings();
                mToolbar.setNavigationIcon(R.mipmap.ajustes_boton_normal);
            }
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                mSettingsFragment.onOpenSettings();
                mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void loadSettings()
    {
        //SHARED PREFERENCES AND SETTINGS FRAGMENT
        prefs = PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        mSettingsFragment = (SettingsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.settings_fragment);
    }

    public InformationAdapter getAdapter()
    {
        return adapter;
    }

}
