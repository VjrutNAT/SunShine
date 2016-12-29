package com.demo.vjrutnat.sunshine.SunShine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.vjrutnat.sunshine.Adapter.WeatherAdapter;
import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SunShine.OnShowDetailsListener, FragmentDetails.OnShowSettingListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SunShine sunShine = SunShine.newInstance();
        transaction.replace(R.id.container, sunShine);
        transaction.commit();
    }


    @Override
    public void onShowDetails() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentDetails fragmentDetails = FragmentDetails.newInstance();
        transaction.replace(R.id.container, fragmentDetails);
        transaction.addToBackStack("FragmentDetails");
        transaction.commit();
    }

    @Override
    public void onShowSetting() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentSetting fragmentSetting = FragmentSetting.newInstance();
        transaction.replace(R.id.container, fragmentSetting);
        transaction.addToBackStack("FragmentSetting");
        transaction.commit();
    }
}
