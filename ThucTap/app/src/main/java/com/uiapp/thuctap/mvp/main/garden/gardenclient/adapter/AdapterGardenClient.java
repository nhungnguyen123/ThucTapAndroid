package com.uiapp.thuctap.mvp.main.garden.gardenclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AdapterGardenClient extends RecyclerView.Adapter {


    public List<Garden> mList = null;
    public ArrayList<Garden> mArrayList;

    public AdapterGardenClient(List<Garden> mListGarden) {
        this.mList = mListGarden;
        this.mArrayList = new ArrayList<Garden>();

        for (int i = 0; i < mList.size(); i++) {
            mArrayList.add(mList.get(i));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gardenClient = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_garden_client, null);
        return new GardenClientHolder(gardenClient);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GardenClientHolder gardenClientHolder = (GardenClientHolder) holder;
        gardenClientHolder.mTvNameGardenClient.setText(mList.get(position).getName());
        gardenClientHolder.mTvAddressGardenClient.setText(mList.get(position).getAddress());
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        mList.clear();
        if (charText.length() == 0) {
            mList.addAll(mArrayList);

        } else {
            for (Garden wp : mArrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mList.add(wp);
                }
            }
        }
        notifyDataSetChanged();

    }
}
