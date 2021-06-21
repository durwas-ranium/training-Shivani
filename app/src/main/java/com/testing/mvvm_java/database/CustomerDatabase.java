package com.testing.mvvm_java.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.testing.mvvm_java.dao.CustomerDao;
import com.testing.mvvm_java.pojo.companyPojo.Customer;

@Database(entities = {Customer.class},version = 1,exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {

    private static CustomerDatabase instance;
    public abstract CustomerDao customerDao();

    private static final String DB_NAME = "customer_db";

    public static synchronized CustomerDatabase getInstance(Context context) {

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CustomerDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return instance;

    }


}
