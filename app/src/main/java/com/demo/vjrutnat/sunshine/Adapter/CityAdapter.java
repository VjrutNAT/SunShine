package com.demo.vjrutnat.sunshine.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.vjrutnat.sunshine.Items.CourseCity;
import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;

import java.util.ArrayList;

/**
 * Created by VjrutNAT on 1/3/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHoller> {
    private Context mContext;
    private ArrayList<CourseCity> mDataCity;
    private LayoutInflater mInflater;

    public CityAdapter(Context mContext, ArrayList<CourseCity> mDataCity) {
        this.mContext = mContext;
        this.mDataCity = mDataCity;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CityViewHoller onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.items_city, parent, false);
        CityViewHoller cityViewHoller = new CityViewHoller(view);
        return cityViewHoller;
    }

    @Override
    public void onBindViewHolder(CityViewHoller holder, int position) {

        CourseCity courseCity = mDataCity.get(position);
        holder.tvCity.setText(courseCity.getNameCity());
        holder.tvCityId.setText(courseCity.getId());
    }

    @Override
    public int getItemCount() {
        return mDataCity.size();
    }

    public static class CityViewHoller extends RecyclerView.ViewHolder {

        TextView tvCity;
        TextView tvCityId;

        public CityViewHoller(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
            tvCityId = (TextView) itemView.findViewById(R.id.tv_city_id);
        }
    }
}
