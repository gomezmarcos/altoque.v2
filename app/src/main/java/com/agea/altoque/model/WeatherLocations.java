package com.agea.altoque.model;

/**
 * Created by lbais on 16/04/2015.
 */
public class WeatherLocations extends Information {


    private String name;
    private double lat;
    private double lng;
    private boolean state=false;
    

    public WeatherLocations(String name, double lat, double lng)
    {
        setName(name);
        setLat(lat);
        setLng(lng);
        defineViewType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


    @Override
    public void defineViewType() {

        InformationTypeEnum result = InformationTypeEnum.STORY_WEATHER_LOCATION;
        this.setViewType(result.getViewTypeId());

    }
}
