package com.testing.mvvm_java.activity;

import androidx.appcompat.app.AppCompatActivity;


import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.testing.mvvm_java.ApiInterface;
import com.testing.mvvm_java.R;
import com.testing.mvvm_java.RetrofitClient;
import com.testing.mvvm_java.SessionManager;
import com.testing.mvvm_java.adapter.CustomerAdapter;
import com.testing.mvvm_java.pojo.companyPojo.AllCompanyResponse;
import com.testing.mvvm_java.pojo.companyPojo.Customer;
import com.testing.mvvm_java.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton floatingActionButton;
    private  View dialogView;
    private EditText customerName;
    private Button addCustomer;
    private Button getListCompany;
    private  Dialog dialog;
    private CustomerViewModel customerViewModel;
    private Customer customer;
    private List<Customer> customersList;
    private List<String> companyName;
    private CustomerAdapter customerAdapter;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 0, limit = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initInstance();
        setListeners();
      //  hitCompanyListApi();

        System.out.println(">>>>>> response access token MAIN " + SessionManager.getInstance(this).getAccessToken());

    }



    private void initView() {

        mRecyclerView = findViewById(R.id.recylerView);
        floatingActionButton = findViewById(R.id.fab_btn);
        getListCompany = findViewById(R.id.getCompanyBtn);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);
    }

    private void initInstance() {

        customersList = new ArrayList();
        companyName = new ArrayList<>();
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);


                customerViewModel.getLiveList().observe(MainActivity.this, new Observer<List<Customer>>() {
                    @Override
                    public void onChanged(List<Customer> customers) {

                        if (customers.size() > 0) {
                            customersList = customers;
                            setDataListInAdapter(customersList);
                        }else {

                        }
                    }
                });

    }

    private void setListeners() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        getListCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CompanyListActivity.class);
                startActivity(intent);
            }
        });

    }


    private void showDialog() {

        dialogView = LayoutInflater.from(this).inflate(R.layout.activity_add_user,null);
        dialog = new Dialog(this,R.style.DialogTheme);

        // Hide the Title bar of this activity screen
      //  dialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(dialogView);


        customerName = dialog.findViewById(R.id.userNameAddUser);
        addCustomer = dialog.findViewById(R.id.userSummitBtn);

        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid(customerName)){
                    System.out.println(">>>>>> customersList call add ");
                   InsertaUpdateFunction(customerName.getText().toString());
//                    customerAdapter.notifyDataSetChanged();

                }

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.show();
    }

    private boolean isValid(EditText text){
        if (TextUtils.isEmpty(text.getText())){
            customerName.setError("Enter Customer Name");
            customerName.setFocusable(true);
            return false;
        }
        else {
            return true;
        }


    }

    public void InsertaUpdateFunction(final String string) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (customersList.size() > 0) {
                    for (int i = 0; i < customersList.size(); i++) {
                        System.out.println(">>>>>> customersList " + customersList.get(i).getName());
                        if (customersList.get(i).getName().equals(string)) {
                            dialog.dismiss();
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "Customer Is already available", Toast.LENGTH_LONG).show();
                            Looper.loop();
                            break;

                        }
                    }
                    customer = new Customer(string);
                    System.out.println(">>>>>>> InsertaUpdateFunction main insert " + customer);
                    customerViewModel.insert(customer);
                    dialog.dismiss();
                }else {
                    customer = new Customer(string);
                    System.out.println(">>>>>>> InsertaUpdateFunction main insert " + customer);
                    customerViewModel.insert(customer);
                    dialog.dismiss();
                }

            }
        });
        thread.start();


    }

    private void setDataListInAdapter(final List<Customer> itemsList1) {

        if (itemsList1.size() > 0) {

            customerAdapter = new CustomerAdapter(this, itemsList1,customerViewModel,"Main");
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(customerAdapter);
            mRecyclerView.setHasFixedSize(false);
            customerAdapter.notifyDataSetChanged();

        }
    }
}