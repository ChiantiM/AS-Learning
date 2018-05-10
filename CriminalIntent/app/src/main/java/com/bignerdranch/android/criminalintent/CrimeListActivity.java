package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Log.d(" CrimeListActivity", "正在实现createFragment中，new了CrimeListFragment，还没有用到");
        return new CrimeListFragment();
    }
}
