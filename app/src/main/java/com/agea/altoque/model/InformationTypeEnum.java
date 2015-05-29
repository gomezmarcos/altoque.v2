package com.agea.altoque.model;

/**
 * Created by marcos on 26/05/15.
 */
public enum InformationTypeEnum {
    STORY_GALLERY_AND_DESCRIPTION(3), STORY_IMAGE_AND_DESCRIPTION(4), STORE_ONLY_TEXT(5), STORY_WEATHER_LOCATION(6), STORY_CONTENT_ITEM(7), WEATHER_CARD(8);
    private int viewTypeId;

    InformationTypeEnum(int viewTypeId){
        this.viewTypeId = viewTypeId;
    }

    public int getViewTypeId() {
        return viewTypeId;
    }

    public static InformationTypeEnum getEnum(int value){
        for (InformationTypeEnum e : InformationTypeEnum.values()){
            if (e.getViewTypeId() == value){
                return e;
            }
        }

        throw new RuntimeException("El parametro value no corresponde que ningun valor de los enums");

    }
}
