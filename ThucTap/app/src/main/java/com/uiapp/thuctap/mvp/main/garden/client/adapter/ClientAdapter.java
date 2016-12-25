package com.uiapp.thuctap.mvp.main.garden.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.mvp.main.garden.client.view.ClientFragment;
import com.uiapp.thuctap.utils.LogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by hongnhung on 7/30/16.
 */
public class ClientAdapter extends BaseAdapter {


    public OnClickBoss mOnClickBoss;

    public void setOnclickBoss(OnClickBoss mOnclickBoss) {
        this.mOnClickBoss = mOnclickBoss;
    }

    Context mContext;
    LayoutInflater inflater;
    public List<User> mList = null;
    public ArrayList<User> mArrayList;


    public ClientAdapter(Context context, List<User> mList) {
        mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
        this.mArrayList = new ArrayList<User>();
        for (int i = 0; i < mList.size(); i++) {
            mArrayList.add(mList.get(i));
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_boss_client, null);
            holder.mTvNameBoss = (TextView) convertView.findViewById(R.id.tv_name_boss);
            holder.mTvEmailBoss = (TextView) convertView.findViewById(R.id.tv_email_boss);
            holder.mLnBoss = (LinearLayout) convertView.findViewById(R.id.ln_item_boss);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvNameBoss.setText(mList.get(position).getDisplayName() + "");
        holder.mTvEmailBoss.setText(mList.get(position).getEmail());
        LogUtils.logE("namedis", mList.get(position).getDisplayName() + "");

        holder.mLnBoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickBoss.setBossChoice(mList.get(position).getId());
                LogUtils.logE("positionId", mList.get(position).getId() + "");
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView mTvNameBoss;
        TextView mTvEmailBoss;
        LinearLayout mLnBoss;

    }


    public void filter(String charText) {
        LogUtils.logE("nameDislay", mArrayList.get(0).getDisplayName() + "");
        charText = charText.toLowerCase(Locale.getDefault());
        mList.clear();
        if (charText.length() == 0) {
            mList.addAll(mArrayList);

        } else {
            for (User wp : mArrayList) {
                if (wp.getDisplayName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mList.add(wp);
                }
            }
        }
        notifyDataSetChanged();

    }

    public interface OnClickBoss {
        void setBossChoice(String idBoss);

    }

}
