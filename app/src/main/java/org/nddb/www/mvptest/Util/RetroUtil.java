package org.nddb.www.mvptest.Util;

import org.nddb.www.mvptest.Interface.RequestIInterface;
import org.nddb.www.mvptest.RetrofitClient.RetrofitClient;

/**
 * Created by admin on 09/28/2017.
 */

public class RetroUtil {
    public static final String BASE_URL = "https://reqres.in/";
    public RetroUtil() {
    }
    public static RequestIInterface requestInterface() {
        return RetrofitClient.getClient(BASE_URL).create(RequestIInterface.class);
    }
}
