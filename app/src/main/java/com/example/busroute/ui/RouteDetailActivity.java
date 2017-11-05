package com.example.busroute.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.busroute.R;
import com.example.busroute.data.remote.RouteModel;
import com.example.busroute.ui.custom.Route;
import com.squareup.picasso.Picasso;

/**
 * Created by dasari on 05/11/17.
 */

public class RouteDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        RouteModel.Routes selectedRoute = getIntent().getParcelableExtra("ROUTE_INFO");
        Parcelable [] parcelableStops = getIntent().getParcelableArrayExtra("STOPS_INFO");
        RouteModel.Stops [] stops = new RouteModel.Stops[parcelableStops.length];
        for(int i = 0; i < parcelableStops.length; i++){
            stops[i] = (RouteModel.Stops)parcelableStops[i];
        }

        TextView routeName = findViewById(R.id.route_name);
        TextView routeDesc = findViewById(R.id.route_description);
        ImageView routeImage = findViewById(R.id.route_image);

        routeName.setText(selectedRoute.getName());
        routeDesc.setText(selectedRoute.getDescription());
        Picasso.with(this).load(selectedRoute.getImage()).into(routeImage);
        Route routeView = findViewById(R.id.route);
        routeView.initialise(stops);
    }
}
