package org.nddb.www.mvptest.Itrector;

import android.content.Context;
import android.util.Log;

import org.nddb.www.mvptest.Constant.Constant;
import org.nddb.www.mvptest.DbHelper.DbHelper;
import org.nddb.www.mvptest.Interface.RequestIInterface;
import org.nddb.www.mvptest.Model.User;
import org.nddb.www.mvptest.Presenter.ImainPreenter;
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

public class MainSycnInretor implements ImainIntercor {
    public MainSycnInretor(MainView context) {
    }

    @Override
    public Long CheckValidaion(String userName, Context c) {
        DbHelper dbHelper = new DbHelper(c);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Constant.fieldUserName, userName);
        return dbHelper.insertRecordInDB(Constant.tableUser, hashMap);
    }

    @Override
    public ArrayList<HashMap<String, String>> getData(Context c) {
        String qry = "select * from " + Constant.tableUser;
        DbHelper dbHelper = new DbHelper(c);
        ArrayList<HashMap<String, String>> array = dbHelper.selectRecordFromDb(qry, null, false);
        return array;
    }

    @Override
    public Void getUsers(final ImainPreenter context) {
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
                            context.DataResponse(user);
                            // mainPresnter.DataResponse(user);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        context.DataError(e);
                        //  mainPresnter.DataError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return null;
    }


}
