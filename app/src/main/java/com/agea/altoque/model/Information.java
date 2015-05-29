package com.agea.altoque.model;

/**
 * Created by marcos on 26/05/15.
 */
public abstract class Information {
    private int viewType;

    public int getViewType(){
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public abstract void defineViewType();
}
