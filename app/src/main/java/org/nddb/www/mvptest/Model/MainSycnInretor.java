package org.nddb.www.mvptest.Model;

import android.content.Context;

import org.nddb.www.mvptest.Constant.Constant;
import org.nddb.www.mvptest.DbHelper.DbHelper;
import org.nddb.www.mvptest.View.MainView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/27/2017.
 */

public class MainSycnInretor implements ImainIntercor{


    public MainSycnInretor(MainView context) {
    }

    @Override
    public Long CheckValidaion(String userName, Context c) {
        DbHelper dbHelper = new DbHelper(c);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put(Constant.fieldUserName,userName);

        return dbHelper.insertRecordInDB(Constant.tableUser,hashMap);
    }

    @Override
    public ArrayList<HashMap<String,String>> getData(Context c) {

        String qry = "select * from "+Constant.tableUser;
        DbHelper dbHelper = new DbHelper(c);
        ArrayList<HashMap<String,String>> array = dbHelper.selectRecordFromDb(qry,null,false);


        return array;
    }


}
