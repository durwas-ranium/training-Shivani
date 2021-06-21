package com.testing.mvvm_java.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.testing.mvvm_java.ApiInterface;
import com.testing.mvvm_java.Constants;
import com.testing.mvvm_java.RetrofitClient;
import com.testing.mvvm_java.SessionManager;
import com.testing.mvvm_java.dao.CustomerDao;
import com.testing.mvvm_java.pojo.companyPojo.AllCompanyResponse;
import com.testing.mvvm_java.pojo.companyPojo.Customer;
import com.testing.mvvm_java.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerViewModel extends AndroidViewModel {

    private CustomerRepository customerRepository;
    public MutableLiveData<List<Customer>> liveCustomerList1;
    public MutableLiveData<List<Customer>> liveCompanyList;
    public LiveData<List<Customer>> liveCustomerList;
    public CustomerDao customerDao;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMGVlNGJmNDE4MTFmOWY4ZjQ5MzQ2YTU4NGNkYzczNmZmNjI1MTcyNjI0ZmVlYzE1YjNhYzE1OTg2NzQxNDZjZDkwYTkxZTc0Y2Q5MGI3MGEiLCJpYXQiOjE2MjM5MTM0MDUsIm5iZiI6MTYyMzkxMzQwNSwiZXhwIjoxNjU1NDQ5NDA1LCJzdWIiOiIyOCIsInNjb3BlcyI6W119.f0C_sgPHhHl69jzjSRMAVVsfmBly3rk_YsZcw0FG0iqOWNaDPor5sx2QvtFHCtkF8sn1hCT8pSHshQb1ThE5ppfmDOoBkxVzpfqQAXrLYC-zd21HFocLu9ujDwbRmvHbtvMevaX10Opec5V8u16zn_QsZ3GJePTBjAbLZhsmjD-bY3q5Bj-FtaBZcmtmkZIl6GodV9IvwiXG6aqDja0aJITpoxlBs1ug88whH_1Eeq1TA8CYHO-7VJwTZyMM6JmTzff92OiTgZ15vTTUyipIYtnRYG2fpZNUdV6GaT5N1yG5i1dcLy-380kdMBjtzIqzyPoh1Gx7btITx1PaTz40_VEyBQTZE4GAGlHxObhXQgOH2vpwoadLtujv-i7fACdzFsmSHp2N0zbSza0qG6lEBSJvMVC1jELQP6FfJrw1xsECoolIREAjhUg19PUgbhwfu1SmJ0eO4SFURM0B6H91AEnNiv2sYM2K8zNpNJnmU8hGeUaSR1aO2x8-p3IO80jNx5N0whhmk6Sq21WDUcXmmSlRVHTArKUjXRsFOx9DuQwqDmudeB2rcWsc3ACvIJfwZsm4mQAaEDEKOlKt6g8i6A3FfKXjtBJmrBntn34qmBs_00VOnAsOeFxZn849DWENLbzZ5g_n7Sjgb92Jgxr2YNEL8tfL-WWP6rcd4rt-3rE";

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
        customerDao = customerRepository.customerDao;
        liveCustomerList1 = new MutableLiveData();
        liveCompanyList = new MutableLiveData();
        liveCustomerList = customerRepository.getLiveData();
       // hitCompanyListApi();

    }

    public void insert(Customer customer) {
        customerRepository.insert(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }
    public void getList() {
        customerRepository.getCustomerList();
    }

    public LiveData<List<Customer>> getLiveList(){
      return liveCustomerList;
    }

    public LiveData<List<Customer>> getLiveCompanyList(){
        return hitCompanyListApi();
    }

    public MutableLiveData<List<Customer>> hitCompanyListApi(){

        List<Customer> companyList = new ArrayList();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.CONTENT_TYPE,Constants.CONTENT_TYPE_VALUE);
        hashMap.put(Constants.AUTHORIZATION,Constants.AUTHORIZATION_VALUE + SessionManager.getInstance(getApplication().getApplicationContext()).getAccessToken());

        Call<AllCompanyResponse> request = new RetrofitClient().getClient(hashMap)
                .create(ApiInterface.class).getAllCompanyResponse();

        request.enqueue(new Callback<AllCompanyResponse>() {
            @Override
            public void onResponse(Call<AllCompanyResponse> call, Response<AllCompanyResponse> response) {


                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        companyList.add(new Customer(response.body().getData().get(i).getName()));
                        liveCompanyList.setValue(companyList);

                    }
                }
            System.out.println(">>>>>> response successfull company list vm " + liveCompanyList);

            }

            @Override
            public void onFailure(Call<AllCompanyResponse> call, Throwable t) {

                System.out.println(">>>>>> response fail " + t.getLocalizedMessage());
            }
        });

        return liveCompanyList;
    }
}
