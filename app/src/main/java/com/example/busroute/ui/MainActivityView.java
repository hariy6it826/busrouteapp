package com.example.busroute.ui;

import com.example.busroute.data.remote.RouteModel;

/**
 * Created by dasari on 05/11/17.
 */

public interface MainActivityView {
    void showProgress();
    void hideProgress();
    void updateBusRoutes(RouteModel busRoutes);
}
