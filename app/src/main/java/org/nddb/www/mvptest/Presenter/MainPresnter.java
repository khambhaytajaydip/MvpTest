package org.nddb.www.mvptest.Presenter;

import android.content.Context;
import android.util.Log;

import org.nddb.www.mvptest.Model.MainSycnInretor;
import org.nddb.www.mvptest.View.MainView;

import java.util.ArrayList;
import java.util.HashMap;


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
        long isValid = mainSycn.CheckValidaion(userName,testContext);
        Log.d("jai","log :"+isValid);
        if (isValid > -1) context.onSuccses(isValid);
        else context.onError(isValid);
    }


    @Override
    public void GetData(Context contexts) {
        ArrayList<HashMap<String,String>> list = mainSycn.getData(contexts);
        context.OnSuccesist(list);


    }
}
