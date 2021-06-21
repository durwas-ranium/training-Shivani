package com.testing.mvvm_java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.se.omapi.Session;
import android.widget.ProgressBar;

import com.testing.mvvm_java.ApiInterface;
import com.testing.mvvm_java.Constants;
import com.testing.mvvm_java.R;
import com.testing.mvvm_java.RetrofitClient;
import com.testing.mvvm_java.SessionManager;
import com.testing.mvvm_java.adapter.CustomerAdapter;
import com.testing.mvvm_java.pojo.companyPojo.AllCompanyResponse;
import com.testing.mvvm_java.pojo.companyPojo.Customer;
import com.testing.mvvm_java.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Customer> companyList;
    private CustomerAdapter customerAdapter;
    private CustomerViewModel customerViewModel;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 0, limit = 2;

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMGVlNGJmNDE4MTFmOWY4ZjQ5MzQ2YTU4NGNkYzczNmZmNjI1MTcyNjI0ZmVlYzE1YjNhYzE1OTg2NzQxNDZjZDkwYTkxZTc0Y2Q5MGI3MGEiLCJpYXQiOjE2MjM5MTM0MDUsIm5iZiI6MTYyMzkxMzQwNSwiZXhwIjoxNjU1NDQ5NDA1LCJzdWIiOiIyOCIsInNjb3BlcyI6W119.f0C_sgPHhHl69jzjSRMAVVsfmBly3rk_YsZcw0FG0iqOWNaDPor5sx2QvtFHCtkF8sn1hCT8pSHshQb1ThE5ppfmDOoBkxVzpfqQAXrLYC-zd21HFocLu9ujDwbRmvHbtvMevaX10Opec5V8u16zn_QsZ3GJePTBjAbLZhsmjD-bY3q5Bj-FtaBZcmtmkZIl6GodV9IvwiXG6aqDja0aJITpoxlBs1ug88whH_1Eeq1TA8CYHO-7VJwTZyMM6JmTzff92OiTgZ15vTTUyipIYtnRYG2fpZNUdV6GaT5N1yG5i1dcLy-380kdMBjtzIqzyPoh1Gx7btITx1PaTz40_VEyBQTZE4GAGlHxObhXQgOH2vpwoadLtujv-i7fACdzFsmSHp2N0zbSza0qG6lEBSJvMVC1jELQP6FfJrw1xsECoolIREAjhUg19PUgbhwfu1SmJ0eO4SFURM0B6H91AEnNiv2sYM2K8zNpNJnmU8hGeUaSR1aO2x8-p3IO80jNx5N0whhmk6Sq21WDUcXmmSlRVHTArKUjXRsFOx9DuQwqDmudeB2rcWsc3ACvIJfwZsm4mQAaEDEKOlKt6g8i6A3FfKXjtBJmrBntn34qmBs_00VOnAsOeFxZn849DWENLbzZ5g_n7Sjgb92Jgxr2YNEL8tfL-WWP6rcd4rt-3rE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        initView();
        initInstance();
      //  hitCompanyListApi();

    }

    private void initView() {

        mRecyclerView = findViewById(R.id.recylerViewCompany);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

    }

    private void initInstance() {
        companyList = new ArrayList();
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        customerViewModel.getLiveCompanyList().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                if (customers != null) {
                    companyList = customers;
                    System.out.println(">>>>>> response successfull company list extra " + companyList);
                    setDataListInAdapter(companyList);
                }else {

                }
            }
        });

    }
    private void setDataListInAdapter(final List<Customer> itemsList1) {

        if (itemsList1.size() > 0) {

            customerAdapter = new CustomerAdapter(this, itemsList1,customerViewModel,"Company");
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(customerAdapter);
            mRecyclerView.setHasFixedSize(false);
            customerAdapter.notifyDataSetChanged();

        }
    }

}