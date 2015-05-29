package com.agea.altoque.settings;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.agea.altoque.R;
import com.agea.altoque.model.EventMam;
import com.agea.altoque.settings.weather_screen.SettingsWeatherActivity;
import com.agea.altoque.settings.content_screen.SettingsContentActivity;
import com.agea.altoque.settings.zodiac_screen.SettingsZodiacActivity;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.sleepbot.datetimepicker.time.TimePickerDialog.OnTimeSetListener;

import java.util.Calendar;


public class SettingsFragment extends Fragment
{
	public static final String AJUSTE_HORARIO = "horario";
	public static final String AJUSTE_HORARIO_HORA_DESDE = "hora_desde";
	public static final String AJUSTE_HORARIO_MINUTO_DESDE = "minuto_desde";
	public static final String AJUSTE_HORARIO_HORA_HASTA = "hora_hasta";
	public static final String AJUSTE_HORARIO_MINUTO_HASTA = "minuto_hasta";
	public static final int AJUSTE_HORARIO_HORA_DESDE_DEFAULT = 22;
	public static final int AJUSTE_HORARIO_MINUTO_DESDE_DEFAULT = 0;
	public static final int AJUSTE_HORARIO_HORA_HASTA_DEFAULT = 7;
	public static final int AJUSTE_HORARIO_MINUTO_HASTA_DEFAULT = 0;
	public static final String TIMEPICKER_TAG = "settings - timepicker";
	public static final String AJUSTE_TAMANO_FUENTE = "tamano_fuente";
	private boolean mSettingMam;
	private boolean mSettingsHorario;
	private boolean mSettingsTamanoFuente;
	boolean restartActivity;

	private TextView mHorarioDesdeTextView;
	private TextView mHorarioHastaTextView;
	private ImageView btnDesde;
	private ImageView btnHasta;
	private LinearLayout mLayoutHorarios;

	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	SwitchCompat mToggleMam;
	SwitchCompat mToggleHorario;
	RadioGroup mToggleTamanoFuente;
	RadioButton mButtonNormal;
	RadioButton mButtonBig;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View viewResult = loadUI(inflater, container);

		mSettingMam = prefs.getBoolean(EventMam.AJUSTE_MAM, true);
		mSettingsHorario = prefs.getBoolean(AJUSTE_HORARIO, false);
		mSettingsTamanoFuente = prefs.getBoolean(AJUSTE_TAMANO_FUENTE, false);
		mToggleMam.setChecked(mSettingMam);
		mToggleHorario.setChecked(mSettingsHorario);

        if(mSettingsTamanoFuente == false)
        {
            mButtonNormal.setChecked(true);
        }
        else
        {
            mButtonBig.setChecked(true);
        }

		editor = prefs.edit();

		mToggleMam.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) {

				editor.putBoolean(EventMam.AJUSTE_MAM, mToggleMam.isChecked());

			}
		});

		mToggleHorario.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editor.putBoolean(AJUSTE_HORARIO, mToggleHorario.isChecked());
				toggleLayout(mLayoutHorarios, mToggleHorario.isChecked());
			}
		});


        mButtonBig.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View v)
            {
                if (mButtonBig.isChecked())
                {
                    editor.putBoolean(AJUSTE_TAMANO_FUENTE,	true);
                }
                else
                {
                    editor.putBoolean(AJUSTE_TAMANO_FUENTE,	false);
                }
				restartActivity = true;
			}

		});
        mButtonNormal.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if (mButtonNormal.isChecked())
                {
                    editor.putBoolean(AJUSTE_TAMANO_FUENTE,	false);
                }
                else
                {
                    editor.putBoolean(AJUSTE_TAMANO_FUENTE,	true);
                }
                restartActivity = true;
            }

        });


		updateDisplay(mHorarioDesdeTextView, prefs.getInt(AJUSTE_HORARIO_HORA_DESDE, AJUSTE_HORARIO_HORA_DESDE_DEFAULT),
                                             prefs.getInt(AJUSTE_HORARIO_MINUTO_DESDE,AJUSTE_HORARIO_MINUTO_DESDE_DEFAULT));
		updateDisplay(mHorarioHastaTextView, prefs.getInt(AJUSTE_HORARIO_HORA_HASTA, AJUSTE_HORARIO_HORA_HASTA_DEFAULT),
                                             prefs.getInt(AJUSTE_HORARIO_MINUTO_HASTA,AJUSTE_HORARIO_MINUTO_HASTA_DEFAULT));


		btnDesde.setOnClickListener(new View.OnClickListener()
        {

			@Override
			public void onClick(View v) {
				showTimePicker(mHorarioDesdeTextView);
			}
		});

		btnHasta.setOnClickListener(new View.OnClickListener()
        {

			@Override
			public void onClick(View v) {
				showTimePicker(mHorarioHastaTextView);
			}
		});

		toggleLayout(mLayoutHorarios, mSettingsHorario);
		return viewResult;
	}

	protected void toggleLayout(LinearLayout layout, boolean enable)
    {
		if (enable)
			layout.setVisibility(LinearLayout.VISIBLE);
		else
			layout.setVisibility(LinearLayout.GONE);

	}

	private View loadUI(LayoutInflater inflater, ViewGroup container)
    {
		final View viewResult = inflater.inflate(R.layout.activity_perfil, container,false);
		mToggleMam = (SwitchCompat) viewResult.findViewById(R.id.toggle_mam);
		mToggleHorario = (SwitchCompat) viewResult.findViewById(R.id.toggle_horario);
		mToggleTamanoFuente = (RadioGroup) viewResult.findViewById(R.id.toggle_tamano_fuente);
        mButtonBig = (RadioButton) viewResult.findViewById(R.id.radio_big);
        mButtonNormal = (RadioButton) viewResult.findViewById(R.id.radio_normal);
		mLayoutHorarios = (LinearLayout) viewResult.findViewById(R.id.layout_horarios);
		mHorarioDesdeTextView = (TextView) viewResult.findViewById(R.id.text_hora_desde);
		mHorarioHastaTextView = (TextView) viewResult.findViewById(R.id.text_hora_hasta);
		btnDesde = (ImageView) viewResult.findViewById(R.id.btn_desde);
		btnHasta = (ImageView) viewResult.findViewById(R.id.btn_hasta);
		LinearLayout zodiac_config = (LinearLayout) viewResult.findViewById(R.id.zodiac_config);
		LinearLayout clima_config = (LinearLayout) viewResult.findViewById(R.id.clima_config);
        LinearLayout content_configuration = (LinearLayout) viewResult.findViewById(R.id.config_content);

		zodiac_config.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				settingsHoroscopoScreen();
			}
		});

        content_configuration.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				settingsContentScreen();
			}
		});

		clima_config.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SettingsWeatherActivity.editor = editor;
				SettingsWeatherActivity.prefs = prefs;
				Intent advance_clima = new Intent(Intent.ACTION_MAIN);
				advance_clima.setClass(viewResult.getContext(), SettingsWeatherActivity.class);
				advance_clima.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
						Intent.FLAG_ACTIVITY_SINGLE_TOP |
						Intent.FLAG_ACTIVITY_NEW_TASK);
				viewResult.getContext().startActivity(advance_clima);
			}
		});

		return viewResult;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()
				.getBaseContext());
	}

	@Override
	public void onPause() {
		if (getView().getVisibility() == View.VISIBLE)
			onCloseSettings();
		super.onPause();
	}

	public void onOpenSettings()
    {
		if (editor == null)
			editor = prefs.edit();
		/*TODO EDIT THIS REF
		getActivity().findViewById(R.id.clock_icon).setEnabled(false);
		getActivity().findViewById(R.id.imageButtonDestacada).setEnabled(false)
		*/
	}

	public void onCloseSettings()
    {
		/*TODO ON CLOSE*/
		editor.commit();
		/*
		if (restartActivity)
        {
			SampleFragment frg = (SampleFragment) getFragmentManager().findFragmentById(R.id.ptr_fragment);
			frg.setBrief(false);
			MainActivity.forceRefresh = true;
			MainActivity.isLoading = true;
			frg.handleNewsResponse();
		}
		//Toast.makeText(getActivity(), "",Toast.LENGTH_LONG).show();
		getActivity().findViewById(R.id.clock_icon).setEnabled(true);
		getActivity().findViewById(R.id.imageButtonDestacada).setEnabled(true);
		*/
	}

	private void showTimePicker(final TextView textView)
	{

		Calendar c = Calendar.getInstance();
		int pHour;
		int pMinute;
		try
		{
			pHour = Integer.parseInt(textView.getText().subSequence(0, 2).toString());
			pMinute = Integer.parseInt(textView.getText().subSequence(3, 5).toString());
		}
		catch (NumberFormatException e)
		{

			pHour = c.get(Calendar.HOUR_OF_DAY);
			pMinute = c.get(Calendar.MINUTE);
		}

		TimePickerDialog.OnTimeSetListener listener = new OnTimeSetListener() {

			@Override
			public void onTimeSet(RadialPickerLayout view, int hourOfDay,
					int minute) {
				updateDisplay(textView, hourOfDay, minute);
			}
		};

		TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
				listener, pHour, pMinute, true, false);

		timePickerDialog.show(getFragmentManager(), TIMEPICKER_TAG);

	}

	private void updateDisplay(TextView textView, int hour, int minute)
    {
		switch (textView.getId())
        {
			case R.id.text_hora_desde:
				editor.putInt(AJUSTE_HORARIO_HORA_DESDE, hour);
				editor.putInt(AJUSTE_HORARIO_MINUTO_DESDE, minute);
				break;
			case R.id.text_hora_hasta:
				editor.putInt(AJUSTE_HORARIO_HORA_HASTA, hour);
				editor.putInt(AJUSTE_HORARIO_MINUTO_HASTA, minute);
				break;
		}

		textView.setText(new StringBuilder().append(String.format("%02d", hour)).append(":").append(String.format("%02d", minute)));
	}

    private void settingsContentScreen()
    {
		SettingsContentActivity.prefs = prefs;
		SettingsContentActivity.editor = editor;
        Intent i = new Intent(getActivity(), SettingsContentActivity.class);
        startActivity(i);
    }

	private void settingsHoroscopoScreen()
	{
		SettingsZodiacActivity.prefs = prefs;
		SettingsZodiacActivity.editor = editor;
		Intent i = new Intent(getActivity(), SettingsZodiacActivity.class);
		startActivity(i);
	}
}