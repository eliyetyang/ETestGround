package com.test.eliyetyang.testground.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.eliyetyang.testground.R;
import com.test.eliyetyang.testground.adapter.holder.RecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by eliyetyang on 16-1-26.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<String> mDataset;

    public RecyclerAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        TextView textView = (TextView) root.findViewById(R.id.recycler_item_tv);
        CardView cardView = (CardView) root.findViewById(R.id.recycler_item_card);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(root, textView, cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.cardView.setZ(0);
        holder.textView.setText(mDataset.get(position));
        holder.cardView.animate().z(50).setDuration(1000).start();
//        if (position % 2 == 1) {
//            holder.cardView.setElevation(10);
//        } else {
//            holder.cardView.setElevation(20);
//        }

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
