package org.nddb.www.mvptest.Presenter;

import android.content.Context;

import org.nddb.www.mvptest.Model.User;
import org.nddb.www.mvptest.View.MainView;

/**
 * Created by admin on 09/27/2017.
 */

public interface ImainPreenter {
    public void AttemptoLogin(String userName);
    public void GetData(Context c);
    public void GetDataFromServer(MainView context);
    public void DataResponse(User user);
    public void DataError(Throwable throwable);

}
