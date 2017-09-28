package org.nddb.www.mvptest.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 09/27/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "MvpTest.sqlite";
    public SQLiteDatabase sqLiteDatabase;
    public Context context;
    public String DB_PATH;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        try {
            createDatabase();
            openDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openDatabase() {
        String path = DB_PATH + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

    }

    private void createDatabase() {
        boolean checkDb = checkDatabaseExist();

        if (checkDb) {

        } else {
            sqLiteDatabase = this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    private void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(DB_NAME);
        String OutFile = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(OutFile);
        byte[] buffet = new byte[1024];
        int lenght;
        while ((lenght = inputStream.read(buffet)) > 0) {
            outputStream.write(buffet, 0, lenght);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private boolean checkDatabaseExist() {
        File file = new File(DB_PATH + DB_NAME);
        return file.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }


    public ArrayList<HashMap<String, String>> selectRecordFromDb(String qry, String[] args, boolean status) {
        ArrayList<HashMap<String, String>> arrayList = null;
        try {
            HashMap<String, String> mapRow;
            Cursor cursor = sqLiteDatabase.rawQuery(qry, args);
            if (cursor.moveToFirst()) {
                arrayList = new ArrayList<>();
                do {
                    mapRow = new HashMap<>();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        mapRow.put(cursor.getColumnName(i), cursor.getString(i));
                    }
                    arrayList.add(mapRow);
                } while (cursor.moveToNext());
                if (cursor != null && cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }


    public long insertRecordInDB(String tableName,
                                 HashMap<String, Object> objInsertMap) {
        long isInsertSuccess = 1;
        try {
            ContentValues contentValues = new ContentValues();
            for (String dbColumnName : objInsertMap.keySet()) {
                Object value = objInsertMap.get(dbColumnName);
                if (value.getClass().equals(Integer.TYPE)) {
                    contentValues.put(dbColumnName, (Integer) value);
                } else if (value.getClass().equals(Float.TYPE)) {
                    contentValues.put(dbColumnName, (Float) value);
                } else if (value.getClass().equals(Long.TYPE)) {
                    contentValues.put(dbColumnName, (Long) value);
                } else if (value.getClass().equals(Double.TYPE)) {
                    contentValues.put(dbColumnName, (Double) value);
                } else {
                    contentValues.put(dbColumnName, value.toString());
                }
            }
            isInsertSuccess = sqLiteDatabase.insert(tableName, null, contentValues);

        } catch (Exception e) {
            Log.e("jai", "insertRecordInDB() " + e, e);
            isInsertSuccess = -1;
        }
        return isInsertSuccess;
    }

    public void deleteRecordQuery(String qry)
    {
        sqLiteDatabase.execSQL(qry);

    }
    public boolean updateRecord(String table_name,
                                HashMap<String, Object> queryValues, String whereClause,
                                String whereArgs[])
    {
        boolean isUpdateSuccess = false;

        try {
            ContentValues contentValues = new ContentValues();
            for (String dbColumnName : queryValues.keySet()) {
                Object value = queryValues.get(dbColumnName);
                if (value.getClass().equals(Integer.TYPE)) {
                    contentValues.put(dbColumnName, (Integer) value);
                } else if (value.getClass().equals(Float.TYPE)) {
                    contentValues.put(dbColumnName, (Float) value);
                } else if (value.getClass().equals(Long.TYPE)) {
                    contentValues.put(dbColumnName, (Long) value);
                } else if (value.getClass().equals(Double.TYPE)) {
                    contentValues.put(dbColumnName, (Double) value);
                } else {
                    contentValues.put(dbColumnName, value.toString());
                }

                if (sqLiteDatabase.update(table_name, contentValues, whereClause,
                        whereArgs) > 0)
                    isUpdateSuccess = true;
                else
                    isUpdateSuccess = false;
            }
        } catch (Exception e) {
            Log.e("update", "updateRecord() " + e, e);
            isUpdateSuccess = false;
        }
        return isUpdateSuccess;
        // dataBase.close();
    }

}

