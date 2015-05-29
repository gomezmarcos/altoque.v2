package com.agea.altoque.settings.content_screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.agea.altoque.R;

import com.agea.altoque.adapter.SettingRowsAdapter;
import com.agea.altoque.model.ContentItem;
import com.agea.altoque.model.Information;

import java.util.ArrayList;
import java.util.List;

public class SettingsContentActivity extends AppCompatActivity
{
    public static SharedPreferences.Editor editor;
    public static SharedPreferences prefs;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    private SettingRowsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<Information> contentItems;
    private ContentSettingsController contentSettingsController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_content);
        contentSettingsController = new ContentSettingsController(this, editor);

        contentItems = new ArrayList<Information>();

        contentItems.add(new ContentItem(getResources().getString(R.string.content_0), 0, prefs.getBoolean("content_0", true)));
        contentItems.add(new ContentItem(getResources().getString(R.string.content_1), 1, prefs.getBoolean("content_1", true)));
        contentItems.add(new ContentItem(getResources().getString(R.string.content_2), 2, prefs.getBoolean("content_2", true)));
        contentItems.add(new ContentItem(getResources().getString(R.string.content_3), 3, prefs.getBoolean("content_3", true)));
        contentItems.add(new ContentItem(getResources().getString(R.string.content_4), 4, prefs.getBoolean("content_4", true)));

        initUI();
    }


    private void initUI()
    {

        mToolbar = (Toolbar) findViewById(R.id.mytoolbar_settings);

        mRecyclerView = (RecyclerView) findViewById(R.id.content_settings);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView toolbar_text = (TextView) this.findViewById(R.id.toolbar_text);
        toolbar_text.setText("Ajustes | Contenido");

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SettingRowsAdapter(contentItems, contentSettingsController);
        mRecyclerView.setAdapter(mAdapter);

    }


    public SettingRowsAdapter getmAdapter() {
        return mAdapter;
    }


    public List<Information> getContentItems() {
        return contentItems;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
