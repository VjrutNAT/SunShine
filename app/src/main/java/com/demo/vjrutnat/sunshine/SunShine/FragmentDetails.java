package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.demo.vjrutnat.sunshine.R;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class FragmentDetails extends Fragment {

    private OnShowSettingListener sCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sCallBack = (OnShowSettingListener) context;
    }

    public FragmentDetails() {
    }

    public static FragmentDetails newInstance(){
        FragmentDetails fragmentDetails = new FragmentDetails();
        Bundle bundle = new Bundle();
        fragmentDetails.setArguments(bundle);
        return fragmentDetails;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_setting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.items_setting:
                sCallBack.onShowSetting();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface OnShowSettingListener{
        void onShowSetting();
    }
}
