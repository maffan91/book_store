package com.app.maffan.bookbank;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import org.greenrobot.greendao.database.Database;

/**
 * Created by maffan on 12/16/2016.
 */

public class App extends Application {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"book_bank");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
