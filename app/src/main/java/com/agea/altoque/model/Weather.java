package com.agea.altoque.model;


import com.agea.altoque.R;
import com.agea.altoque.helpers.AvailableImageValues;

import org.joda.time.DateTime;

public class Weather extends Information
{
	public static final String AJUSTE_CLIMA = "clima";
	public static final String AJUSTE_MODE = "clima_mode";
	public static final String AJUSTE_CITY = "clima_city";

	private static int[] values0 = { 0, 8 };
	private static AvailableImageValues icons0 = new AvailableImageValues(values0);
	private static int[] values1 = { 1, 9, 16 };
	private static AvailableImageValues icons1 = new AvailableImageValues(values1);
	private static int[] values2 = { 2, 12, 15 };
	private static AvailableImageValues icons2 = new AvailableImageValues(values2);
	private static int[] values3 = { 3, 6, 11, 13, 17 };
	private static AvailableImageValues icons3 = new AvailableImageValues(values3);
	private static int[] values4 = { 4, 14 };
	private static AvailableImageValues icons4 = new AvailableImageValues(values4);
	private static int[] values5 = { 5, 7 };
	private static AvailableImageValues icons5 = new AvailableImageValues(values5);
	private static int[] values6 = { 10 };
	private static AvailableImageValues icons6 = new AvailableImageValues(values6);

	private ItemWeather item;
	private String generatedDate;
	private String status;



	@Override
	public void defineViewType()
	{
		InformationTypeEnum result = InformationTypeEnum.WEATHER_CARD;
		this.setViewType(result.getViewTypeId());
	}
	public String getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}

	public ItemWeather getItem() {
		return item;
	}

	public DateTime getDate()
	{
		DateTime date = new DateTime(Long.parseLong(generatedDate));
		return date;
	}

	public static int getBackgroundDrawableIdFromIcon(String icon)
	{

		int icono = Integer.parseInt(icon);
		int background;

		if (icons0.containsIcon(icono))
		{
			background = R.mipmap.background0;
		}
		else if (icons1.containsIcon(icono))
		{
			background = R.mipmap.background1;
		}
		else if (icons2.containsIcon(icono))
		{
			background = R.mipmap.background2;

		}
		else if (icons3.containsIcon(icono))
		{
			background = R.mipmap.background3;
		}
		else if (icons4.containsIcon(icono))
		{
			background = R.mipmap.background4;
		}
		else if (icons5.containsIcon(icono))
		{
			background = R.mipmap.background5;
		}
		else if (icons6.containsIcon(icono))
		{
			background = R.mipmap.background6;
		}
		else
		{
			background=0;
		}

		return background;
	}



}
