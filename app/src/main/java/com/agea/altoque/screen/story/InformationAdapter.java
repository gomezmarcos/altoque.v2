package com.agea.altoque.screen.story;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agea.altoque.model.Information;
import com.agea.altoque.model.Story;
import com.agea.altoque.model.ViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 26/05/15.
 */
public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Information> informations;

    private InformationAdapter(){}
    public InformationAdapter(List<Information> informations)
    {
        this.informations = informations == null ? new ArrayList<Information>() : informations;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  ViewHolderFactory.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Information information = informations.get(position);
        ViewHolderFactory.populate(holder, information);

    }

    @Override
    public int getItemCount() {
        return informations.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.informations.get(position).getViewType();
    }

    public void setInformations(List<Information> informations) {
        this.informations = informations;
    }

    public List<Information> getInformations() {
        return informations;
    }
}
