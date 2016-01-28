package com.test.eliyetyang.testground.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eliyetyang on 15-7-9.
 */
public class LoopTextView extends TextView {
    private Handler mHandler;
    private ArrayList<LoopItem> mLoopResIds;
    private int mCurIndex = 0;

    public LoopTextView(Context context) {
        super(context);
        init();
    }

    public LoopTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoopTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (mCurIndex >= mLoopResIds.size()) {
                    mCurIndex = 0;
                }
                LoopItem loopItem = mLoopResIds.get(mCurIndex);
                setBackgroundResource(loopItem.DrawableResId);
                setText(loopItem.text);
                mHandler.sendEmptyMessageDelayed(0, 1000);
                mCurIndex++;
                return false;
            }
        });
    }

    public void setLoopResIds(ArrayList<LoopItem> resIds) {
        mLoopResIds = resIds;
        LoopItem loopItem = mLoopResIds.get(mCurIndex);
        setBackgroundResource(loopItem.DrawableResId);
        setText(loopItem.text);
        if (resIds.size() >= 1) {
            mHandler.sendEmptyMessageDelayed(0, 1000);
            mCurIndex = 1;
        }
    }

    public static class LoopItem {
        int DrawableResId;
        String text;

        public LoopItem(int drawableResId, String text) {
            DrawableResId = drawableResId;
            this.text = text;
        }
    }
}
