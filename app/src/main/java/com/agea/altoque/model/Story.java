package com.agea.altoque.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcos on 26/05/15.
 */
public class Story extends Information{
    private String description;
    private String video;
    private List<String> images;
    private DateTime date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null || description.trim().isEmpty() ? "" : description;
    }

    public void setImages(JSONArray images) throws JSONException {


        if (images.length() == 0)
            this.images = new ArrayList<>();
        else {
            List<String> result = new ArrayList<String>();
            for (int i = 0; i < images.length() ; i++){
                result.add(images.getString(i));
            }
            this.images =  result;
        }

    }


    public void setVideo(String video) {
        this.video = video == null || video.trim().isEmpty() ? "" : video;
    }

    public String getVideo() {
        return video;
    }

    @Override
    public void defineViewType() {

        InformationTypeEnum result = InformationTypeEnum.STORE_ONLY_TEXT;

        if(!description.isEmpty() && !images.isEmpty() && images.size() > 1){
            result = InformationTypeEnum.STORY_GALLERY_AND_DESCRIPTION;
        }

        if(!description.isEmpty() && !images.isEmpty() && images.size() == 1){
            result = InformationTypeEnum.STORY_IMAGE_AND_DESCRIPTION;
        }

        this.setViewType(result.getViewTypeId());
    }
    public void setDate(String fecha) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        this.date = formatter.parseDateTime(fecha);
    }

    public DateTime getDate() {
        return date;
    }

    public List<String> getImages() {
        return images;
    }


}
