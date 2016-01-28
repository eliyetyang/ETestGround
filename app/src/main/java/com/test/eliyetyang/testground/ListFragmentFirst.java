package com.test.eliyetyang.testground;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eliyetyang on 15-5-28.
 */
public class ListFragmentFirst extends Fragment {
    private ListView mListView;

    private ArrayList<FirstListData> mDatas;

    private LayoutInflater mLayoutInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
        //添加的假数据
        for (int i = 0; i < 20; i++) {
            FirstListData firstListData = new FirstListData("item " + i);
            mDatas.add(firstListData);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_first, null);
        mListView = (ListView) view.findViewById(R.id.list_fragment_first_lv);

        mLayoutInflater = inflater;//因为后面的adapter中的getview方法需要这个东对象来加载试图所以暂时村下来

        mListView.setAdapter(new ThisAdapter());
        return view;
    }

    private class ThisAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //这里返回数据的总行数
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            //这里是重用机制的判断
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.list_fragment_first_item, null);
                TextView textView = (TextView) convertView.findViewById(R.id.list_fragment_first_item_name);
                viewHolder = new ViewHolder(textView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //这里取出数据源
            FirstListData firstListData = mDatas.get(position);

            //这这里根据数据源为界面赋值
            viewHolder.name.setText(firstListData.getName());
            return convertView;
        }
    }

    /**
     * 用来缓存convertiew中  子试图的对象引用
     * 这样可以加快速度
     */
    private class ViewHolder {
        TextView name;

        public ViewHolder(TextView name) {
            this.name = name;
        }
    }
}
