package org.nddb.www.mvptest.View;

import org.nddb.www.mvptest.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/27/2017.
 */

public interface MainView {
    void setProgressBarVisiblity(int v);
    void onSuccses(long l);
    void onError(long l);
    void OnSuccesist(ArrayList<HashMap<String, String>> list);
    void OnUsersSucces(User user);
    void onUserResError(String s);

}
