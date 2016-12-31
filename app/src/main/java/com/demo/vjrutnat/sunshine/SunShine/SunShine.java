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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.vjrutnat.sunshine.Adapter.WeatherAdapter;
import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;
import com.demo.vjrutnat.sunshine.Utils.UrlWeather;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class SunShine extends Fragment {

    private OnShowDetailsListener dCallBack;
    private ArrayList<Weather> data;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dCallBack = (OnShowDetailsListener) context;
    }

    public static SunShine newInstance() {
        SunShine sunShine = new SunShine();
        return sunShine;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunshine, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_information);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        final TextView tvTempMax = (TextView) view.findViewById(R.id.tv_temperature_to);
        final TextView tvTempMin = (TextView) view.findViewById(R.id.tv_temperature_from);
        final TextView tvClouds = (TextView) view.findViewById(R.id.tv_static);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(UrlWeather.CURRENT_WEATHER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray weather = response.optJSONArray("weather");
                JSONObject clouds = weather.optJSONObject(0);
                String description = clouds.optString("description");
                String date = response.optString("dt");
                JSONObject temperature = response.optJSONObject("main");
                int temperatureMax = temperature.optInt("temp_max");
                int temperatureMin = temperature.optInt("temp_min");

                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getDefault();
                calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date currentTimeZone=new java.util.Date((long)Integer.parseInt(date)*1000);
                calendar.setTime(currentTimeZone);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                int tempMax = temperatureMax - 273;
                int tempMin = temperatureMin - 273;
                tvDate.setText(getDayName(dayOfWeek));
                tvClouds.setText(description);
                tvTempMax.setText("" + tempMax + "℃");
                tvTempMin.setText("" + tempMin + "℃");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.newInstance().addRequestQueue(jsonObjectRequest, "currentweather");
        data = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(UrlWeather.WEEK_WEATHER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                for (int i = 0 ; i < response.length(); i++){
                    JSONArray list = response.optJSONArray("list");
                    JSONObject obj = list.optJSONObject(i+1);
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

                    Calendar calendar = Calendar.getInstance();
                    TimeZone tz = TimeZone.getDefault();
                    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date currentTimeZone=new java.util.Date((long)Integer.parseInt(date)*1000);
                    calendar.setTime(currentTimeZone);
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    int temperatureMax = tempMax - 273;
                    int temperatureMin = tempMin - 273;
                    Weather weekWeather = new Weather(getDayName(dayOfWeek),clouds,temperatureMin + "℃",temperatureMax + "℃");
                    data.add(weekWeather);
                }
                WeatherAdapter adapter = new WeatherAdapter(getActivity(), data);
                recyclerView.setAdapter(adapter);
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
        void onShowDetails();
    }

    public static String getDayName(int day){
        switch(day){
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
                return  "Friday";
            case 7:
                return "Saturday";
        }

        return "Worng Day";
    }

}
