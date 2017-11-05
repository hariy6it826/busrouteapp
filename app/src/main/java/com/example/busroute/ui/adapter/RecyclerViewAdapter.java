package com.example.busroute.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.busroute.R;
import com.example.busroute.data.remote.RouteModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Dasari on 05/11/17.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private PublishSubject<RouteModel.Routes> publishSubject = PublishSubject.create();

    private Context mContext;
    private ArrayList<RouteModel.Routes> mValues;

    public RecyclerViewAdapter(Context context, ArrayList<RouteModel.Routes> values) {
        mValues = values;
        mContext = context;
    }

    public PublishSubject<RouteModel.Routes> getPublishSubject(){
        return publishSubject;
    }
    public void setData(ArrayList<RouteModel.Routes> routes){
        mValues = routes;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        RouteModel.Routes route;
        ImageView contentImage;
        TextView routeName;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(view -> publishSubject.onNext(route));
        }

        public void setData(RouteModel.Routes route) {
            this.route = route;
            contentImage = itemView.findViewById(R.id.route_image_view);
            routeName = itemView.findViewById(R.id.route_name);
            Picasso.with(mContext).load(Uri.parse(route.getImage())).into(contentImage);
            routeName.setText(route.getName());
        }

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.bus_route_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

}