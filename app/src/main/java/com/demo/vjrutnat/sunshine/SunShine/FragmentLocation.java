package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.demo.vjrutnat.sunshine.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by VjrutNAT on 1/6/2017.
 */

public class FragmentLocation extends Fragment implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private Button mBtnCheckLcation;
    private OnShowSunShineListener mCallBackSunShine;
    private String mLatitude;
    private String mLongitude;

    public FragmentLocation() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBackSunShine = (OnShowSunShineListener) context;
    }

    public static FragmentLocation newInstance(){
        FragmentLocation fragmentLocation = new FragmentLocation();
        return fragmentLocation;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        mBtnCheckLcation = (Button) view.findViewById(R.id.btn_showsunshine);
        buildGoogleApiClient();
        mBtnCheckLcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocation != null) {
                    mCallBackSunShine.onShowSunShine(mLongitude, mLatitude);
                } else {
                    Toast.makeText(getActivity(), "Not of found" , Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude() + "";
            mLongitude = mLocation.getLongitude() + "";
        } else {
            Toast.makeText(getActivity(), "Not of found" , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }


    public interface OnShowSunShineListener {
        void onShowSunShine(String lon, String lat);
    }

}