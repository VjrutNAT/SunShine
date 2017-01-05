package com.demo.vjrutnat.sunshine.SunShine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.vjrutnat.sunshine.Adapter.WeatherAdapter;
import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;
import com.demo.vjrutnat.sunshine.Utils.UrlWeather;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class SunShine extends Fragment {

    public static final String TAG = SunShine.class.getName();
    public static OnShowDetailsListener mCallBackShowDetails;
    private ArrayList<Weather> mData;
    private RecyclerView mRecyclerView;
    private ProgressBar mPrgLoad;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBackShowDetails = (OnShowDetailsListener) context;
    }

    public static SunShine newInstance() {
        SunShine sunShine = new SunShine();
        return sunShine;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunshine, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_information);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mPrgLoad = (ProgressBar) view.findViewById(R.id.pb_load);
        final TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        final TextView tvTempMax = (TextView) view.findViewById(R.id.tv_temperature_to);
        final TextView tvTempMin = (TextView) view.findViewById(R.id.tv_temperature_from);
        final TextView tvClouds = (TextView) view.findViewById(R.id.tv_static);
        final ImageView imvWeather = (ImageView) view.findViewById(R.id.imv_weather);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(UrlWeather.CURRENT_WEATHER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray weather = response.optJSONArray("weather");
                JSONObject clouds = weather.optJSONObject(0);
                String description = clouds.optString("description");
                String icon = clouds.optString("icon");
                String date = response.optString("dt");
                JSONObject temperature = response.optJSONObject("main");
                int temperatureMax = temperature.optInt("temp_max");
                int temperatureMin = temperature.optInt("temp_min");

                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getDefault();
                calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                java.util.Date currentTimeZone = new java.util.Date((long) Integer.parseInt(date) * 1000);
                calendar.setTime(currentTimeZone);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);

                int tempMax = temperatureMax - 273;
                int tempMin = temperatureMin - 273;
                tvDate.setText("Today, " + getMonthName(month) + " " + day);
                tvClouds.setText(description);
                tvTempMax.setText("" + tempMax + "℃");
                tvTempMin.setText("" + tempMin + "℃");
                Picasso.with(getActivity()).load(UrlWeather.ICON_WEATHER_URL + icon + ".png").into(imvWeather);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.newInstance().addRequestQueue(jsonObjectRequest, "currentweather");
        mData = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(UrlWeather.WEEK_WEATHER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONArray list = response.optJSONArray("list");
                    JSONObject obj = list.optJSONObject(i + 1);
                    String date = obj.optString("dt");
                    JSONObject temp = obj.optJSONObject("temp");
                    int tempMax = temp.optInt("max");
                    int tempMin = temp.optInt("min");
                    int pressure = obj.optInt("pressure");
                    int humidity = obj.optInt("humidity");
                    double speed = obj.optDouble("speed");
                    JSONArray weather = obj.optJSONArray("weather");
                    JSONObject objWeather = weather.optJSONObject(0);
                    String clouds = objWeather.optString("description");
                    String icon = objWeather.optString("icon");


                    Calendar calendar = Calendar.getInstance();
                    TimeZone tz = TimeZone.getDefault();
                    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date currentTimeZone = new java.util.Date((long) Integer.parseInt(date) * 1000);
                    calendar.setTime(currentTimeZone);
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int temperatureMax = tempMax - 273;
                    int temperatureMin = tempMin - 273;
                    String dateFormat = getMonthName(month) + " " + day;
                    Weather weatherDetails = new Weather(getDayName(dayOfWeek), clouds, temperatureMax + "℃", temperatureMin + "℃", humidity + "",
                            pressure + "", speed + "", icon, dateFormat);
                    mData.add(weatherDetails);
                }
                WeatherAdapter adapter = new WeatherAdapter(getActivity(), mData);
                mRecyclerView.setAdapter(adapter);

                mPrgLoad.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.newInstance().addRequestQueue(objectRequest, "weekweather");
        return view;
    }

    public interface OnShowDetailsListener {
        void onShowDetails(String mDay, String mStatus, String mTempMax, String mTempMin, String mIcon, String mHumidity,
                           String mPressure, String mWind, String mDate);

    }

    private String getDayName(int day) {
        switch (day) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }

        return "Wrong Day";
    }

    private String getMonthName(int month) {
        switch (month) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
        }
        return "Wrong month";
    }

}
