package com.test.eliyetyang.testground.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by eliyetyang on 16-1-26.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public CardView cardView;

    public RecyclerViewHolder(View itemView, TextView textView, CardView cardView) {
        super(itemView);
        this.textView = textView;
        this.cardView = cardView;
    }
}
