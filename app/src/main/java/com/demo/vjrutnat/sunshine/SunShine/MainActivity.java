package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.vjrutnat.sunshine.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements FragmentLocation.OnShowSunShineListener, SunShine.OnShowDetailsListener {
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentLocation fragmentLocation = FragmentLocation.newInstance();
        transaction.replace(R.id.container, fragmentLocation);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(MainActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(MainActivity.this, data);
                Log.i(TAG, "Place: " + place.getName());
                Log.d(TAG, " lon :" + place.getLatLng());
                LatLng latLong = place.getLatLng();
                String lat = latLong.latitude + "";
                String lon = latLong.longitude + "";
                onShowSunShine(lon, lat);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(MainActivity.this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
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
    public void onShowSunShine(String lon, String lat) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SunShine sunShine = new SunShine().newInstance(lon, lat);
        transaction.replace(R.id.container, sunShine);
        transaction.commit();
    }
}
