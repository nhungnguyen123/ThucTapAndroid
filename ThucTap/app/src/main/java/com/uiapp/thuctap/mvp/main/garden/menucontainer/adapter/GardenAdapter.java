package com.uiapp.thuctap.mvp.main.garden.menucontainer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.model.Garden;

import java.util.List;

/**
 * Created by hongnhung on 7/28/16.
 */
public class GardenAdapter extends BaseAdapter implements SpinnerAdapter {
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */


    private Context context;
    private List<Garden> mItems;

    public GardenAdapter(Context context, List<Garden> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_garden, parent, false);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.ln_item);
        TextView tvGardenName = (TextView) convertView.findViewById(R.id.tv_kiosk_name);

        for (Garden garden : mItems) {
            if (garden.isChecked()) garden.setChecked(false);
        }

        final Garden k = (Garden) getItem(position);
        k.setChecked(true);
        tvGardenName.setText(k.getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_garden, parent, false);

        TextView mTvName = (TextView) convertView.findViewById(R.id.tv_kiosk_name);
        final Garden garden = mItems.get(position);
        mTvName.setText(garden.getName());
        if (garden.isChecked()) {
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.ln_item);
            layout.setBackgroundResource(R.color.colorBackgroundGardenItem);
        }

        return convertView;
    }
}
