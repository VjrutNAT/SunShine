package com.demo.vjrutnat.sunshine.SunShine;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.vjrutnat.sunshine.R;

public class MainActivity extends AppCompatActivity implements SunShine.OnShowDetailsListener, FragmentDetails.OnShowSettingListener, FragmentSetting.OnShowListCityListener
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                onShowSetting();
                break;
            case R.id.action_map:
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onShowDetails(String mDay, String mStatus, String mTempMax, String mTempMin, String mIcon, String mHumidity, String mPressure, String mWind, String mDate) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentDetails fragmentDetails = FragmentDetails.newInstance(mDay, mStatus, mTempMax, mTempMin, mIcon, mHumidity, mPressure, mWind, mDate);
        transaction.replace(R.id.container, fragmentDetails);
        transaction.addToBackStack(FragmentDetails.TAG);
        transaction.commit();
    }


    @Override
    public void onShowListCity() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentCity fragmentCity = FragmentCity.newInstance();
        transaction.replace(R.id.container, fragmentCity);
        transaction.addToBackStack("FragmentCity");
        transaction.commit();
    }
}
