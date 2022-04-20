package com.datamation.swdsfa.reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.datamation.swdsfa.R;
import com.datamation.swdsfa.model.Target;

import java.text.NumberFormat;
import java.util.ArrayList;

public class TargetVsAchievementAdapterNew extends BaseAdapter {
    Context context;
    ArrayList<Target> targetData;
    LayoutInflater layoutInflater;
    Target targetModel;
    private NumberFormat numberFormat = NumberFormat.getInstance();
    public TargetVsAchievementAdapterNew(Context context, ArrayList<Target> targetData) {
        this.context = context;
        this.targetData = targetData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return targetData.size();
    }

    @Override
    public Object getItem(int i) {
        return targetData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = view;
        if (rowView==null) {
            rowView = layoutInflater.inflate(R.layout.target_vs_actual_row_responsive_layout, null, true);
        }
        //link views

        TextView brandCode = rowView.findViewById(R.id.brandCode);
        TextView brandName = rowView.findViewById(R.id.brandName);
        TextView target = rowView.findViewById(R.id.target);
        TextView achievement = rowView.findViewById(R.id.achievement);
        TextView precentage = rowView.findViewById(R.id.precentage);

        targetModel = targetData.get(position);


        brandCode.setText(targetModel.getBrandCode());
        brandName.setText("" + numberFormat.format(targetModel.getBrandName()));
        target.setText("" + numberFormat.format(targetModel.getTargetAmt()));
        achievement.setText("" + numberFormat.format(targetModel.getAchieveAmt()));
        precentage.setText("" + numberFormat.format(targetModel.getPrecentage()));

        return rowView;
    }
}

