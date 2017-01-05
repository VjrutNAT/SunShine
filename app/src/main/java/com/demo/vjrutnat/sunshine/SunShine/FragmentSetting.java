package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.vjrutnat.sunshine.R;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class FragmentSetting extends Fragment implements View.OnClickListener{

    private OnShowListCityListener cCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cCallBack = (OnShowListCityListener) context;
    }

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
        TextView tvLocation = (TextView) view.findViewById(R.id.tv_location);
        TextView tvLocationId = (TextView) view.findViewById(R.id.tv_location_id);
        TextView tvTempUnits = (TextView) view.findViewById(R.id.tv_temperature_units);
        TextView tvUnits = (TextView) view.findViewById(R.id.tv_units);
        tvLocation.setOnClickListener(this);
        tvLocationId.setOnClickListener(this);
        tvTempUnits.setOnClickListener(this);
        tvUnits.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_location:
                cCallBack.onShowListCity();
                break;
            case R.id.tv_location_id:
                cCallBack.onShowListCity();
                break;
            case R.id.tv_temperature_units:
                break;
            case R.id.tv_units:
                break;
        }
    }



    public interface OnShowListCityListener{
        void onShowListCity();
    }
}
