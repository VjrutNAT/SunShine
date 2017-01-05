package com.demo.vjrutnat.sunshine.SunShine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.demo.vjrutnat.sunshine.Adapter.CityAdapter;
import com.demo.vjrutnat.sunshine.Items.CourseCity;
import com.demo.vjrutnat.sunshine.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by VjrutNAT on 1/3/2017.
 */

public class FragmentCity extends Fragment {

    private RecyclerView mRecyclerViewCity;
    private ArrayList<CourseCity> mData;
    private ProgressBar mProgressBar;

    public FragmentCity() {
    }

    public static FragmentCity newInstance() {
        FragmentCity fragmentCity = new FragmentCity();
        Bundle bundle = new Bundle();
        fragmentCity.setArguments(bundle);
        return fragmentCity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        mRecyclerViewCity = (RecyclerView) view.findViewById(R.id.rcv_city);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewCity.setLayoutManager(layoutManager);
        mProgressBar = (ProgressBar) view.findViewById(R.id.prg_city);
        new CityAsyncTask().execute();
        return view;
    }

    private class CityAsyncTask extends AsyncTask<Void, Void, ArrayList<CourseCity>> {

        @Override
        protected ArrayList<CourseCity> doInBackground(Void... params) {
            mData = readFileFromAsset();
            return mData;
        }

        @Override
        protected void onPostExecute(ArrayList<CourseCity> courseCities) {
            super.onPostExecute(courseCities);
            CityAdapter cityAdapter = new CityAdapter(getActivity(), mData);
            mRecyclerViewCity.setAdapter(cityAdapter);
            mProgressBar.setVisibility(View.GONE);
        }

    }


    private ArrayList<CourseCity> readFileFromAsset() {
        BufferedReader reader = null;

        ArrayList<CourseCity> mdata = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("citylist.json")));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                Log.d("read json", mLine.toString());
                JSONObject objCityId = new JSONObject(mLine);
                String nameCity = objCityId.optString("name");
                String cityId = objCityId.optString("_id");

                CourseCity courseCity = new CourseCity(nameCity, cityId);
                mdata.add(courseCity);
            }
        } catch (IOException e) {
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return mdata;
    }

}
