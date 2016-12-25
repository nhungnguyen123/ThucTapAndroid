package com.uiapp.thuctap.mvp.main.garden.vegetable.view;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uiapp.thuctap.R;
import com.uiapp.thuctap.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 8/20/16.
 */
public class DatePickerFragment extends DialogFragment {

    private DatePicker fromDate;
    private DatePicker toDate;
    @Bind(R.id.ln_pick_from)
    LinearLayout mLnPickFrom;

    @Bind(R.id.ln_pick_to)
    LinearLayout mLnPickTo;

    @Bind(R.id.tv_from)
    TextView mTvDate;

    public static DatePickerFragment newInstance() {

        Bundle args = new Bundle();
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Date date = new Date(System.currentTimeMillis());
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_pick_date, null, false);
        ButterKnife.bind(this, v);
        fromDate = (DatePicker) v.findViewById(R.id.dpr_from);
        toDate = (DatePicker) v.findViewById(R.id.dpr_to);
        View customTitle = LayoutInflater.from(getContext()).inflate(R.layout.custom_title_dialog, null, false);

        fromDate.updateDate(year, month, day);
        toDate.updateDate(year, month, day + 1);

        return new AlertDialog.Builder(getActivity(), 0)
                .setView(v)
                .setCustomTitle(customTitle)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Date from = new Date(fromDate.getCalendarView().getDate());
                        LogUtils.logE("TAG", "Date: " + from.toString());
                        Date to;
                            to = new Date(toDate.getCalendarView().getDate());
                            LogUtils.logE("TAG", "Date: " + to.toString());
                        if (to.after(from)) {
                            if (getTargetRequestCode() == 200) {
                                Intent intent = new Intent();
                                intent.putExtra("Mode", 0);
                                intent.putExtra("FromDay", formatDate(from));
                                intent.putExtra("ToDay", formatDate(to));
                                intent.putExtra("CurrentDay", to);
                                getTargetFragment().onActivityResult(
                                        getTargetRequestCode(), VegetableFragment.REQUEST_CODE_DIALOG, intent);
                            }
                        } else
                            Toast.makeText(getActivity(), "To Day must be after From Day", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "You haven't just chosen date", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }

    private String formatDate(Date date) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
        return time.substring(0, 10);
    }

}