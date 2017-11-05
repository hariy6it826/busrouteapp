package com.example.busroute.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.busroute.R;
import com.example.busroute.data.remote.RouteModel;
import com.example.busroute.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainPresenter<MainActivityView> mainPresenter;
    private static final String ROUTE_ID = "5808f00d10000005074c6340";
    private RecyclerView mRecyclerView;
    private ArrayList<RouteModel.Routes> mRoutesList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TextView fetchMsgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoutesList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        fetchMsgText = findViewById(R.id.route_fetch_msg);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        mRecyclerView.addItemDecoration(itemDecorator);
        recyclerViewAdapter = new RecyclerViewAdapter(this, mRoutesList);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mainPresenter = new MainPresenter<>();
        mainPresenter.attachView(this);
        mainPresenter.fetchBusRoutes(ROUTE_ID);
        recyclerViewAdapter.getPublishSubject().subscribe(this::showRouteDetailsActivity);

    }

    private void showRouteDetailsActivity(RouteModel.Routes route){
        Log.d(TAG, "Route name: " + route.getName());
        Intent intent = new Intent(this, RouteDetailActivity.class);
        intent.putExtra("ROUTE_INFO", route);
        intent.putExtra("STOPS_INFO", route.getStops());
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        fetchMsgText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        fetchMsgText.setVisibility(View.GONE);
    }

    @Override
    public void updateBusRoutes(RouteModel busRoutes) {
        Log.d(TAG, "Route Fetching success");
        if(busRoutes.getRoutes().length != 0){
            mRoutesList.addAll(Arrays.asList(busRoutes.getRoutes()));
            recyclerViewAdapter.setData(mRoutesList);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
