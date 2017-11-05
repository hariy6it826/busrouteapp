package com.example.busroute.ui;

import android.util.Log;

import com.example.busroute.data.remote.RetrofitManager;
import com.example.busroute.data.remote.RouteModel;
import com.example.busroute.data.remote.RoutesAPI;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dasari on 05/11/17.
 */

public class MainPresenter<V extends MainActivityView> implements MainActivityPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private V mMainActivityView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachView(V view) {
        this.mMainActivityView = view;
    }

    public void detachView() {
        this.mMainActivityView = null;
        compositeDisposable.dispose();
    }

    @Override
    public void fetchBusRoutes(String id) {
        mMainActivityView.showProgress();
        RoutesAPI routesAPI = RetrofitManager.getRetrofitInstance().create(RoutesAPI.class);
        Observable<RouteModel> routesObservable = routesAPI.getRoutes(id);
        routesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RouteModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RouteModel routeModels) {
                        Log.i(TAG, "Success: " + routeModels.toString());
                        mMainActivityView.hideProgress();
                        mMainActivityView.updateBusRoutes(routeModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
