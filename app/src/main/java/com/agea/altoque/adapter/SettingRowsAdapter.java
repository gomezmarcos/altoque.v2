package com.agea.altoque.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.agea.altoque.R;
import com.agea.altoque.model.ContentItem;
import com.agea.altoque.model.Information;
import com.agea.altoque.model.InformationTypeEnum;
import com.agea.altoque.model.WeatherLocations;
import com.agea.altoque.settings.weather_screen.WeatherSettingsController;
import com.agea.altoque.settings.content_screen.ContentSettingsController;
import com.agea.altoque.holder.ViewHolderTextAndCheckbox;
import com.agea.altoque.holder.ViewHolderTextAndRadio;
import java.util.List;


public class SettingRowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Information> items;
    private WeatherSettingsController weatherSettingsController;
    private ContentSettingsController contentSettingsController_checkbox;

    public SettingRowsAdapter(List<Information> items, WeatherSettingsController weatherSettingsController)
    {
        this.weatherSettingsController = weatherSettingsController;
        this.items = items;
    }

    public SettingRowsAdapter(List<Information> items, ContentSettingsController contentSettingsController_checkbox)
    {
        this.contentSettingsController_checkbox = contentSettingsController_checkbox;
        this.items = items;
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=null;
        RecyclerView.ViewHolder vh=null;
        switch(InformationTypeEnum.getEnum(viewType))
        {
            case STORY_WEATHER_LOCATION:
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type_text_and_radio_btn, parent, false);
                vh = new ViewHolderTextAndRadio(v, parent.getContext(), weatherSettingsController);
                break;
            }
            case STORY_CONTENT_ITEM:
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type_text_and_check_btn, parent, false);
                vh = new ViewHolderTextAndCheckbox(v, parent.getContext(), contentSettingsController_checkbox);
                break;
            }

            default:
            {
                break;
            }
        }

        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        if (items.get(position).getViewType() == InformationTypeEnum.STORY_WEATHER_LOCATION.getViewTypeId())
        {
            WeatherLocations location = (WeatherLocations)items.get(position);
            ViewHolderTextAndRadio holderCustom = (ViewHolderTextAndRadio)holder;
            holderCustom.setItemData(location.getName());
            holderCustom.setRadioState(location.isState());
        }
        else if (items.get(position).getViewType() == InformationTypeEnum.STORY_CONTENT_ITEM.getViewTypeId())
        {
            ContentItem contentItem = (ContentItem)items.get(position);
            ViewHolderTextAndCheckbox holderCustom = (ViewHolderTextAndCheckbox)holder;
            holderCustom.setItemData(contentItem.getName());
            holderCustom.setCheckState(contentItem.isState());
        }

    }

    @Override
    public int getItemCount()
    {
        if (items == null)
        {
            return 0;
        }
        else
        {
            return items.size();
        }

    }

    @Override
    public int getItemViewType(int position)
    {
        return items.get(position).getViewType();
    }

}
