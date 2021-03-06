package org.nddb.www.mvptest.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.nddb.www.mvptest.Interface.RequestIInterface;
import org.nddb.www.mvptest.Itrector.MainSycnInretor;
import org.nddb.www.mvptest.Model.User;
import org.nddb.www.mvptest.Util.RetroUtil;
import org.nddb.www.mvptest.View.MainView;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


/**
 * Created by admin on 09/27/2017.
 */

public class MainPresnter implements ImainPreenter {
    MainView context;
    Context testContext;

    MainSycnInretor mainSycn;

    public MainPresnter(MainView context, Context contextTest) {
        this.context = context;
        this.testContext = contextTest;
        this.mainSycn = new MainSycnInretor(context);
    }

    @Override
    public void AttemptoLogin(String userName) {
        long isValid = mainSycn.CheckValidaion(userName, testContext);
        Log.d("jai", "log :" + isValid);
        if (isValid > -1) context.onSuccses(isValid);
        else context.onError(isValid);
    }


    @Override
    public void GetData(Context contexts) {
        ArrayList<HashMap<String, String>> list = mainSycn.getData(contexts);
        context.OnSuccesist(list);
    }

    @Override
    public void GetDataFromServer(final MainView context) {
        mainSycn.getUsers(MainPresnter.this);

        context.setProgressBarVisiblity(View.VISIBLE);
        final User users;
        RequestIInterface requestIInterface = RetroUtil.requestInterface();
        requestIInterface.getUSERS(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<User> userResponse) {
                        Log.d("jai", userResponse.toString());
                        if (userResponse.code() == 200) {
                            User user = userResponse.body();
                            context.OnUsersSucces(user);

                            // mainPresnter.DataResponse(user);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        context.onUserResError(e.toString());
                        Log.d("jai", "map view error" + e.toString());
                        // context.DataError(e);
                        //  mainPresnter.DataError(e);
                    }

                    @Override
                    public void onComplete() {
                        context.setProgressBarVisiblity(View.INVISIBLE);
                    }
                });
    }

    @Override
    public void DataResponse(User user) {
        Log.d("jai", "users :" + user);
        context.OnUsersSucces(user);
    }

    @Override
    public void DataError(Throwable throwable) {
        Log.d("jai", "users :" + throwable.toString());
    }
}
