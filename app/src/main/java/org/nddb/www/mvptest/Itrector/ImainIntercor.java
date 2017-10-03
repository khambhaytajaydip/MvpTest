package org.nddb.www.mvptest.Itrector;

import android.content.Context;

import org.nddb.www.mvptest.Presenter.ImainPreenter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/27/2017.
 */

interface ImainIntercor {
    public Long CheckValidaion(String userName, Context c);
    public ArrayList<HashMap<String,String>> getData(Context c);
    Void getUsers(ImainPreenter context);
}
