package com.uiapp.thuctap.mvp.main.garden.editgarden.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.uiapp.thuctap.R;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.model.VegetableBody;
import com.uiapp.thuctap.mvp.main.garden.garden.adapter.CreateGardenAdapter;
import com.uiapp.thuctap.mvp.main.garden.editgarden.interfacecallback.OnClickAction;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class EditGardenAdapter extends RecyclerView.Adapter {
    List<Garden> list;
    OnClickAction mOnClickAction;
    Context mContext;


    public void setOnClickAction(OnClickAction mOnClickAction) {
        this.mOnClickAction = mOnClickAction;
    }

    public EditGardenAdapter(List<Garden> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View editGardenHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_garden, parent, false);
        return new EditGardenHolder(editGardenHolder);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View vPopup;
        final PopupWindow mPopupEditAccount;
        final EditText mEdtName, mEdtAddress, mEdtArea;
        final Button mBtnUpdate, mBtnCancel;
        final RecyclerView mRvListVegetable;
        final CreateGardenAdapter mVegetableAdapter;
        final String idGarden = list.get(position).getId();
        LogUtils.logE("idGarden", list.get(position).getId());

        final List<Vegetable> mListVegetable = new ArrayList<>();
        final List<VegetableBody> mListVegetablesUpdate = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        vPopup = inflater.inflate(R.layout.popup_edit_garden, null);
        mPopupEditAccount = new PopupWindow(vPopup, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT, true);
        mPopupEditAccount.setBackgroundDrawable(null);
        mPopupEditAccount.setTouchable(true);
        mPopupEditAccount.setOutsideTouchable(true);

        mEdtName = (EditText) vPopup.findViewById(R.id.edt_pop_name_garden);
        mEdtAddress = (EditText) vPopup.findViewById(R.id.edt_pop_address);
        mBtnUpdate = (Button) vPopup.findViewById(R.id.btn_pop_update);
        mBtnCancel = (Button) vPopup.findViewById(R.id.btn_pop_cancel);
        mEdtArea = (EditText) vPopup.findViewById(R.id.edt_area);
        mRvListVegetable = (RecyclerView) vPopup.findViewById(R.id.rv_list_vegetable);

        mListVegetable.addAll(list.get(position).getVegetableList());


        for (int i = 0; i < mListVegetable.size(); i++) {
            mListVegetable.get(i).setChoose(true);
        }

        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mRvListVegetable.setLayoutManager(manager);
        mRvListVegetable.setHasFixedSize(true);

        mVegetableAdapter = new CreateGardenAdapter(list.get(position).getVegetableList());
        mRvListVegetable.setAdapter(mVegetableAdapter);
        mEdtName.setText(list.get(position).getName());
        mEdtAddress.setText(list.get(position).getAddress());
        mEdtArea.setText(list.get(position).getArea());

        final EditGardenHolder editGardenHolder = (EditGardenHolder) holder;
        editGardenHolder.mTvName.setText(list.get(position).getName());
        editGardenHolder.mTvDescription.setText(list.get(position).getDescription());
        editGardenHolder.mTvTotalVegetable.setText(list.get(position).getVegetableList().size() + "");


        /**
         * show fragment detail Garden
         */
        editGardenHolder.mLnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logE("click_detail", position + "");

                String garden = new Gson().toJson(list.get(position), Garden.class);
                mOnClickAction.OnClickDetail(garden);
            }
        });


        /**
         * Show popup to choice delete Garden or edit garden
         */
        editGardenHolder.mImgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickAction.OnClickDelete(list.get(position).getId());
            }
        });


        /**
         * Edit Garden.
         */
        editGardenHolder.mImgEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                mOnClickAction.OnClickChange();
            }
        });

        /**
         * Set Image for garden
         */
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateGardenRequest gardenBodyRequest = new UpdateGardenRequest();
                gardenBodyRequest.setArea(mEdtArea.getText().toString());
                gardenBodyRequest.setName(mEdtName.getText().toString());

                gardenBodyRequest.setAddress(mEdtAddress.getText().toString());
                for (int i = 0; i < list.get(position).getVegetableList().size(); i++) {
                    if (list.get(position).getVegetableList().get(i).isChoose()) {
                        VegetableBody vegetable = new VegetableBody(list.get(position).getVegetableList().get(i).getName(), list.get(position).getVegetableList().get(i).getId());
                        mListVegetablesUpdate.add(vegetable);
                    }
                }
                gardenBodyRequest.setVegetableList(mListVegetablesUpdate);

                mOnClickAction.OnClickChange(idGarden, gardenBodyRequest);
                mPopupEditAccount.dismiss();

            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupEditAccount.dismiss();
            }
        });

    }

    private int getScreenWidth(Context context) {//lay tong chieu ngang man hinh
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
