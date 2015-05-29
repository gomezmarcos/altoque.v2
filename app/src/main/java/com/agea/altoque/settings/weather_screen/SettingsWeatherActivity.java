package com.agea.altoque.settings.weather_screen;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.agea.altoque.R;
import com.agea.altoque.adapter.SettingRowsAdapter;
import com.agea.altoque.model.Information;
import com.agea.altoque.model.Weather;
import com.agea.altoque.model.WeatherLocations;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SettingsWeatherActivity extends AppCompatActivity
{
    public static SharedPreferences.Editor editor;
    public static SharedPreferences prefs;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SettingRowsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Information> locations;
    private SwitchCompat toggle_weather, toggle_location_mode;
    private LinearLayout layout_location_manual, layout_location;
    private TextView location_city;
    private WeatherSettingsController weatherSettingsController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_settings_weather);


        weatherSettingsController = new WeatherSettingsController(this);
        locations = new ArrayList<Information>();

        StringBuilder buf= new StringBuilder();
        try
        {
            InputStream json = this.getApplicationContext().getAssets().open("weather.json");
            BufferedReader in= new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str=in.readLine()) != null) {
                buf.append(str);
            }

            in.close();

        }
        catch (Exception e)
        {
            Log.d("Error abriendo..", String.valueOf(e));
        }

        JSONObject json = null;
        try
        {
            json = new JSONObject(buf.toString());
            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i< items.length();i++)
            {
                JSONObject jsonitem = items.getJSONObject(i);
                String name = jsonitem.getString("name");
                double lat = jsonitem.getDouble("lat");
                double lng = jsonitem.getDouble("lng");
                locations.add( new WeatherLocations(name,lat,lng));
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        initUI();
    }


    private void initUI()
    {

        mToolbar = (Toolbar) findViewById(R.id.mytoolbar_settings);
        toggle_location_mode = (SwitchCompat) findViewById(R.id.toggle_location);
        toggle_weather = (SwitchCompat) findViewById(R.id.toggle_clima);
        mRecyclerView = (RecyclerView) findViewById(R.id.content_settings);

        layout_location= (LinearLayout) findViewById(R.id.layout_location);
        layout_location_manual= (LinearLayout) findViewById(R.id.layout_location_manual);
        location_city = (TextView) findViewById(R.id.location_city);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView toolbar_text = (TextView) this.findViewById(R.id.toolbar_text);
        toolbar_text.setText("Ajustes | Clima");

        toggle_weather.setChecked(prefs.getBoolean(Weather.AJUSTE_CLIMA, true));
        toggle_location_mode.setChecked(prefs.getBoolean(Weather.AJUSTE_MODE, true));



        stateLayout();

        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SettingRowsAdapter(locations, weatherSettingsController);
        mRecyclerView.setAdapter(mAdapter);


        toggle_weather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                editor.putBoolean(Weather.AJUSTE_CLIMA, toggle_weather.isChecked());
                stateLayout();

            }
        });
        toggle_location_mode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                editor.putBoolean(Weather.AJUSTE_MODE, toggle_location_mode.isChecked());
                stateLayout();
            }
        });


    }


    public SettingRowsAdapter getmAdapter() {
        return mAdapter;
    }

    public void setCity(String city)
    {
        location_city.setText(city);
        editor.putString(Weather.AJUSTE_CITY, city);
        editor.commit();


    }

    public List<Information> getLocations() {
        return locations;
    }


    private void stateLayout()
    {
        if (!toggle_weather.isChecked())
        {
            layout_location.setVisibility(View.GONE);
            layout_location_manual.setVisibility(View.GONE);

        }
        else
        {
            layout_location.setVisibility(View.VISIBLE);

            if (toggle_location_mode.isChecked())
            {
                location_city.setText("GPS");
                layout_location_manual.setVisibility(View.GONE);
                editor.commit();

            }
            else
            {
                layout_location_manual.setVisibility(View.VISIBLE);
                weatherSettingsController.radioPressed(prefs.getString(Weather.AJUSTE_CITY, "Buenos Aires"));

            }
        }
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
