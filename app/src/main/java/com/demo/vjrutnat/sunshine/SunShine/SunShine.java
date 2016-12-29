package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.vjrutnat.sunshine.Adapter.WeatherAdapter;
import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;

import java.util.ArrayList;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class SunShine extends Fragment{

    private OnShowDetailsListener dCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dCallBack = (OnShowDetailsListener) context;
    }

    public static SunShine newInstance(){
        SunShine sunShine = new SunShine();
        return sunShine;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunshine, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_information);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Weather> data = new ArrayList<>();
        int n=20;
        for (int i = 0; i <= n; i++ ){
            Weather day = new Weather("today", "status","10"+i,"11"+i,R.mipmap.ic_launcher);
            data.add(day);
        }
        WeatherAdapter adapter = new WeatherAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public interface OnShowDetailsListener{
        void onShowDetails();
    }
}
