package com.softuni.homework5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Product> mData;

    MyAdapter(ArrayList<Product> data) {
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvCategory.setText(mData.get(position).getCategory());
        holder.tvPrice.setText(String.format(Locale.ENGLISH, "%.2f", mData.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCategory, tvPrice;

        MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
