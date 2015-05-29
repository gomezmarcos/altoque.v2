package com.agea.altoque.model;

/**
 * Created by lbais on 16/04/2015.
 */
public class ContentItem extends Information {


    private String name;
    private int id;
    private boolean state = true;


    public ContentItem(String name, int id, boolean state)
    {
        setName(name);
        setId(id);
        setState(state);

        defineViewType();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


    @Override
    public void defineViewType()
    {
        InformationTypeEnum result = InformationTypeEnum.STORY_CONTENT_ITEM;
        this.setViewType(result.getViewTypeId());
    }
}
