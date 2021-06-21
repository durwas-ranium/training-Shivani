package com.testing.mvvm_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.testing.mvvm_java.R;
import com.testing.mvvm_java.pojo.companyPojo.Customer;
import com.testing.mvvm_java.viewmodel.CustomerViewModel;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ItemViewHolder>  {
    private List<Customer> listdata;
    private Context context;
    private String value;
    private AlertDialog.Builder builder;
    private CustomerViewModel customerViewModel;

    public CustomerAdapter(Context context, List<Customer> listdata,CustomerViewModel customerViewModel, String value) {
        this.listdata = listdata;
        this.context = context;
        this.value = value;
        this.customerViewModel = customerViewModel;
    }


    @NonNull
    @Override
    public CustomerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item, parent, false);
        return new CustomerAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ItemViewHolder holder, final int position) {

        holder.name.setText(listdata.get(position).getName());

        if (value == "Company"){

            holder.button.setVisibility(View.GONE);
            System.out.println(">>>>>> company fail " );

        }else {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println(">>>>>> no fail " );
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            customerViewModel.delete(listdata.get(position));

                            // notifyItemRemoved(position);
//
                        }
                    });
                    thread.start();
                }


            });

        }

      //  System.out.println(">>>>> additionalList.get(position).getItemName()" + listdata.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView button;

        public ItemViewHolder(View itemView) {
            super(itemView);
             this.name = itemView.findViewById(R.id.customerName);
             this.button = itemView.findViewById(R.id.deleteBtn);


        }

    }



}
