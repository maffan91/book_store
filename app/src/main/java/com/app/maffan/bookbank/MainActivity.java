package com.app.maffan.bookbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private DaoSession daoSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up the database
        setupDb();
        
        Publisher publisher = new Publisher(null,"A Press","New York,USA");
        insertPublisher(publisher);


    }


    public void setupDb(){

        daoSession = ((App) getApplication()).getDaoSession();

    }


    public void insertPublisher(Publisher publisher){

        PublisherDao publisherDao = daoSession.getPublisherDao();
        publisherDao.insert(publisher);
        Log.d("INSERT", "Publisher Inserted!");
    }

}
