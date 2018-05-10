package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //这里开始加载fragment的视图，里面只有一个RecyclerView组件。
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        //这里开始加载并管理了Recyclerview，但是还没有填充Item
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        //没有LayoutManager的支持，不仅RecyclerView无法工作，还会导致应用崩溃。所
        //以，RecyclerView视图创建完成后，就立即转交给了LayoutManager对象。
        // RecyclerView类不会亲自摆放屏幕上的列表项。实际上，摆放的任务被委托给了
        //LayoutManager。除了在屏幕上摆放列表项，LayoutManager还负责定义屏幕滚动行为。因此，
        // 没有LayoutManager，RecyclerView也就没法正常工作。
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //这个方法new并调用了Adapter
        updateUI();

        return view;
    }





// 定义ViewHolder内部类
    private class CrimeHolder extends RecyclerView.ViewHolder {
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
        }
    }

    //定义adapter内部类,Recycle类会来找这个Adapter加载ViewHolder
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        // 构造方法，接受列表数据
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // 加载当前activity需要的ViewHolder
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            // 用已经指定layout的CrimeViewHolder填充数据
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    //这个方法new并调用了Adapter
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


}
