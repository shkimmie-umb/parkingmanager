package com.example.parkingmanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class purchase_listview_Adapter extends android.widget.BaseAdapter {

    public ArrayList<purchaseTableModel> passList;
    Activity activity;

    public purchase_listview_Adapter(Activity activity, ArrayList<purchaseTableModel> passList) {
        super();
        this.activity = activity;
        this.passList = passList;
    }

    @Override
    public int getCount() {
        return passList.size();
    }

    @Override
    public Object getItem(int position) {
        return passList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView pass_type;
        TextView pass_status;
        TextView pass_purchase_date;
        TextView pass_amount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_purchase_row, null);
            holder = new ViewHolder();
            holder.pass_type = (TextView) convertView.findViewById(R.id.pass_type);
            holder.pass_status = (TextView) convertView.findViewById(R.id.pass_status);
            holder.pass_purchase_date = (TextView) convertView
                    .findViewById(R.id.pass_purchase_date);
            holder.pass_amount = (TextView) convertView.findViewById(R.id.pass_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        purchaseTableModel item = passList.get(position);
        holder.pass_type.setText(item.getPass_type().toString());
        holder.pass_status.setText(item.getPass_status().toString());
        holder.pass_purchase_date.setText(item.getPass_purchase_date().toString());
        holder.pass_amount.setText(item.getPass_amount().toString());

        return convertView;
    }
}
