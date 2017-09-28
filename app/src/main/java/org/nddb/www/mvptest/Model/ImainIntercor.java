package org.nddb.www.mvptest.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/27/2017.
 */

interface ImainIntercor {
    public Long CheckValidaion(String userName, Context c);
    public ArrayList<HashMap<String,String>> getData(Context c);
}
