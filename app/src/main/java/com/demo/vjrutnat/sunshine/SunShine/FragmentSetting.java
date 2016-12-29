package com.demo.vjrutnat.sunshine.SunShine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.vjrutnat.sunshine.R;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class FragmentSetting extends Fragment {

    public FragmentSetting() {
    }

    public static FragmentSetting newInstance(){
        FragmentSetting fragmentSetting = new FragmentSetting();
        Bundle bundle = new Bundle();
        fragmentSetting.setArguments(bundle);
        return fragmentSetting;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        return view;
    }
}
