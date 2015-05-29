package com.agea.altoque.model;


public class Weather extends Information
{
	public static final String AJUSTE_CLIMA = "clima";
	public static final String AJUSTE_MODE = "clima_mode";
	public static final String AJUSTE_CITY = "clima_city";
	private String name;
	private String conditions;
	private String visibility;
	private String temp;
	private String humidity;
	private String wind;
	private String icon;
	private String feels;
	private String pressure;
	private Forecast forecast;
	private String generatedDate;
	private int id;
	private String fecha;
	private String fechaAlmacen;

	public Weather()
	{
		defineViewType();
	}

	public Weather(int id, String fecha, String temp, String name,
				   String icon, String conditions, String visibility, String humidity,
				   String wind, String feels, String pressure, Forecast forecast,
				   String generatedDate, String fechaAlmacen)
	{
		this.id = id;
		this.fecha = fecha;
		this.temp = temp;
		this.name = name;
		this.icon = icon;
		this.conditions = conditions;
		this.visibility = visibility;
		this.humidity = humidity;
		this.wind = wind;
		this.feels = feels;
		this.pressure = pressure;
		this.forecast = forecast;
		this.generatedDate = generatedDate;
		this.fechaAlmacen = fechaAlmacen;

		defineViewType();


	}

	public String getFechaAlmacen() {
		return fechaAlmacen;
	}

	public void setFechaAlmacen(String fechaAlmacen) {
		this.fechaAlmacen = fechaAlmacen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getFeels() {
		return feels;
	}

	public void setFeels(String feels) {
		this.feels = feels;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}

	@Override
	public void defineViewType()
	{
		InformationTypeEnum result = InformationTypeEnum.WEATHER_CARD;
		this.setViewType(result.getViewTypeId());
	}

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}
}
