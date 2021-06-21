package com.testing.mvvm_java.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.testing.mvvm_java.pojo.companyPojo.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
     void addCustomer(Customer customer);

    @Delete
     void deleteCustomer(Customer customer);

    @Update
     void updateCustomer(Customer customer);

    @Query("DELETE FROM customer_table ")
     void deleteAll();

    @Query("SELECT * FROM customer_table")
    List<Customer> getCustomerList();

    @Query("SELECT *  FROM customer_table ")
    LiveData<List<Customer>> getLiveCustomerList();
}
