package com.testing.mvvm_java.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.testing.mvvm_java.Constants;
import com.testing.mvvm_java.dao.CustomerDao;
import com.testing.mvvm_java.database.CustomerDatabase;
import com.testing.mvvm_java.pojo.companyPojo.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    public CustomerDao customerDao;
    public LiveData<List<Customer>> getLiveList;

    public CustomerRepository(Application application) {
        CustomerDatabase customerDatabase = CustomerDatabase.getInstance(application);
        customerDao = customerDatabase.customerDao();
        getLiveList = customerDao.getLiveCustomerList();

    }

    public void insert(Customer customer) {

        new CustomerRepository.InsertCustomerAsyncTask(customerDao).execute(customer);
    }

    public void delete(Customer customer) {

        new CustomerRepository.DeleteCustomerAsyncTask(customerDao).execute(customer);
    }

    public void update(Customer customer) {

        new CustomerRepository.UpdateCustomerAsyncTask(customerDao).execute(customer);
    }

    public void deleteAll() {

        new CustomerRepository.DeleteAllCustomerAsyncTask(customerDao).execute();
    }

    public void getCustomerList() {

        new CustomerRepository.AllCustomerListAsyncTask(customerDao).execute();
    }

    public LiveData<List<Customer>> getLiveData(){

        return getLiveList;
    }


    private class InsertCustomerAsyncTask extends AsyncTask<Customer ,Void, Void> {

        private CustomerDao customerDao;

        public InsertCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.addCustomer(customers[0]);
            return null;
        }
    }

    private class DeleteCustomerAsyncTask extends AsyncTask<Customer ,Void, Void> {

        private CustomerDao customerDao;

        public DeleteCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.deleteCustomer(customers[0]);
            return null;
        }
    }

    private class UpdateCustomerAsyncTask extends AsyncTask<Customer ,Void, Void> {

        private CustomerDao customerDao;

        public UpdateCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.updateCustomer(customers[0]);
            return null;
        }
    }

    private class DeleteAllCustomerAsyncTask extends AsyncTask<Void ,Void, Void> {

        private CustomerDao customerDao;

        public DeleteAllCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.deleteAll();
            return null;
        }
    }

    private class AllCustomerListAsyncTask extends AsyncTask<String ,Void, Void> {

        private CustomerDao customerDao;

        public AllCustomerListAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            customerDao.getCustomerList();
            return null;
        }
    }


}
