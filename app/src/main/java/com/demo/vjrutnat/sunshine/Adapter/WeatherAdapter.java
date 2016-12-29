package com.demo.vjrutnat.sunshine.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.vjrutnat.sunshine.Items.Weather;
import com.demo.vjrutnat.sunshine.R;
import com.demo.vjrutnat.sunshine.SunShine.FragmentDetails;
import com.demo.vjrutnat.sunshine.SunShine.MainActivity;
import com.demo.vjrutnat.sunshine.SunShine.SunShine;

import java.util.ArrayList;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHoller> {

    private Context mContext;
    private ArrayList<Weather> mData;
    private LayoutInflater minflater;

    public WeatherAdapter(Context mContext, ArrayList<Weather> mData) {
        this.mContext = mContext;
        this.mData = mData;
        minflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHoller onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.items_information, parent, false);
        ViewHoller viewHoller = new ViewHoller(view);
        return viewHoller;
    }

    @Override
    public void onBindViewHolder(ViewHoller holder, int position) {

        Weather weather = mData.get(position);
        holder.id.setImageResource(weather.getId());
        holder.day.setText(weather.getDay());
        holder.status.setText(weather.getStatus());
        holder.temperature_from.setText(weather.getTemperutare_from());
        holder.temperature_to.setText(weather.getTemperutare_to());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHoller extends RecyclerView.ViewHolder{

        ImageView id;
        TextView day;
        TextView status;
        TextView temperature_to;
        TextView temperature_from;

        public ViewHoller(View itemView) {
            super(itemView);
            id = (ImageView) itemView.findViewById(R.id.imv_static);
            day = (TextView) itemView.findViewById(R.id.tv_day);
            status = (TextView) itemView.findViewById(R.id.tv_clouds);
            temperature_from = (TextView) itemView.findViewById(R.id.tv_temperature_from);
            temperature_to = (TextView) itemView.findViewById(R.id.tv_temperature_to);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    FragmentDetails fragmentDetails = FragmentDetails.newInstance();
                    transaction.replace(R.id.container, fragmentDetails);
                    transaction.addToBackStack("FragmentDetails");
                    transaction.commit();
                }
            });
        }
    }
}