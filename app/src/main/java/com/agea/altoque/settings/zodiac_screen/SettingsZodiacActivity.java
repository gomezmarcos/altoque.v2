package com.agea.altoque.settings.zodiac_screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.agea.altoque.R;
import com.agea.altoque.model.Zodiac;
import com.agea.altoque.model.ZodiacItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SettingsZodiacActivity extends AppCompatActivity
{
    public static SharedPreferences.Editor editor;
    public static SharedPreferences prefs;
    private ArrayList<Zodiac> horoscopo;


    private Toolbar mToolbar;
    private List<ImageView> images_signs;
    private TextView txt_hoy, txt_bienestar, txt_amor, txt_riqueza;
    private SwitchCompat toggle_horoscopo;
    private LinearLayout chamu;
    private int signoSelected;

    private ZodiacSettingsController zodiacSettingsController;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_horoscopo);
        images_signs = new ArrayList<ImageView>();
        zodiacSettingsController = new ZodiacSettingsController(this, editor);
        signoSelected = prefs.getInt("horoscopo_position", -1);
        getSignos();

        initUI();
    }


    private void initUI()
    {

        mToolbar = (Toolbar) findViewById(R.id.mytoolbar_settings);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView toolbar_text = (TextView) this.findViewById(R.id.toolbar_text);
        toolbar_text.setText(getResources().getString(R.string.ajustes_horoscopo));

        toggle_horoscopo = (SwitchCompat) findViewById(R.id.toggle_horoscopo);

        toggle_horoscopo.setChecked(prefs.getBoolean(Zodiac.AJUSTE_HOROSCOPO, true));



        toggle_horoscopo.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                editor.putBoolean(Zodiac.AJUSTE_HOROSCOPO, toggle_horoscopo.isChecked());
            }
        });



        txt_hoy = (TextView) findViewById(R.id.txt_hoy);
        txt_amor = (TextView) findViewById(R.id.txt_amor);
        txt_riqueza = (TextView) findViewById(R.id.txt_riqueza);
        txt_bienestar = (TextView) findViewById(R.id.txt_bienestar);

        ImageView aries = (ImageView) findViewById(R.id.aries);
        ImageView tauro = (ImageView) findViewById(R.id.tauro);
        ImageView geminis = (ImageView) findViewById(R.id.geminis);
        ImageView cancer = (ImageView) findViewById(R.id.cancer);
        ImageView leo = (ImageView) findViewById(R.id.leo);
        ImageView virgo = (ImageView) findViewById(R.id.virgo);
        ImageView libra = (ImageView) findViewById(R.id.libra);
        ImageView escorpio = (ImageView) findViewById(R.id.escorpio);
        ImageView sagitario = (ImageView) findViewById(R.id.sagitario);
        ImageView capricornio = (ImageView) findViewById(R.id.capricornio);
        ImageView acuario = (ImageView) findViewById(R.id.acuario);
        ImageView piscis = (ImageView) findViewById(R.id.piscis);


        chamu = (LinearLayout) findViewById(R.id.chamu);


        images_signs.add(aries);
        images_signs.add(tauro);
        images_signs.add(geminis);
        images_signs.add(cancer);
        images_signs.add(leo);
        images_signs.add(virgo);
        images_signs.add(libra);
        images_signs.add(escorpio);
        images_signs.add(sagitario);
        images_signs.add(capricornio);
        images_signs.add(acuario);
        images_signs.add(piscis);



        for (int i = 0 ; i< images_signs.size(); i++)
        {
            images_signs.get(i).setOnClickListener(zodiacSettingsController);
        }

        chamu.setVisibility(View.GONE);
        setGreyIcons();

        if (signoSelected>-1)
        {
            images_signs.get(signoSelected).setBackgroundColor(getResources().getColor(R.color.red_alert));
            setHoroscopo();
        }


    }

    private void getSignos()
    {
        /*
        if (horoscopo == null)
        {
            DatabaseHelper db = new DatabaseHelper(this);
            horoscopo = db.getLastHoroscopo();

            //Log.d("Horoscopo", String.valueOf(horoscopo));
            //Log.d("Horoscopo size", String.valueOf(horoscopo.size()));
            db.closeDB();
        }
        */

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

    public void setGreyIcons()
    {
        for (int i = 0 ; i< images_signs.size(); i++)
        {
            images_signs.get(i).setBackgroundColor(getResources().getColor(R.color.grey_background_horoscopo));
        }
    }

    public void refreshSigno()
    {
        signoSelected = prefs.getInt("horoscopo_position", -1);
    }

    public void setHoroscopo()
    {
        if(horoscopo != null)
        {
            if(signoSelected>-1)
            {
                JSONArray jsonArray = null;
                try
                {
                    jsonArray = new JSONArray(horoscopo.get(signoSelected).getData());
                    List<ZodiacItem> items = processSign(jsonArray);

                    txt_hoy.setText(items.get(0).getDescripcion());
                    txt_amor.setText(items.get(1).getDescripcion());
                    txt_riqueza.setText(items.get(2).getDescripcion());
                    txt_bienestar.setText(items.get(3).getDescripcion());
                    chamu.setVisibility(View.VISIBLE);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                chamu.setVisibility(View.GONE);
            }



        }
        else
        {
            chamu.setVisibility(View.GONE);
        }



    }

    private List<ZodiacItem> processSign(JSONArray jsonSignData)	throws JSONException
    {
        List<ZodiacItem> zodiacItems = new ArrayList<ZodiacItem>();
        for (int i = 0; i < jsonSignData.length(); i++) {
            JSONObject objSignData = jsonSignData.getJSONObject(i);
            int id = objSignData.getInt("id");
            String name = objSignData.getString("nombre");
            String description = objSignData.getString("descripcion");
            zodiacItems.add(new ZodiacItem(id, name, description));
        }

        return zodiacItems;
    }
}


