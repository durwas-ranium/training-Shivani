package com.testing.mvvm_java.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.testing.mvvm_java.ApiInterface;
import com.testing.mvvm_java.Constants;
import com.testing.mvvm_java.R;
import com.testing.mvvm_java.RetrofitClient;
import com.testing.mvvm_java.SessionManager;
import com.testing.mvvm_java.pojo.AuthResponse;
import com.testing.mvvm_java.pojo.AuthResponseError;
import com.testing.mvvm_java.pojo.AuthRquest;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText userEmail;
   private EditText password;
   private Button loginButton;
   private ProgressBar progressBar;
   private AlertDialog.Builder builder;
   private String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initInstance();
        setListeneres();

        System.out.println(">>>>>> response access token login " + SessionManager.getInstance(this).getAccessToken());
    }

    private void initView() {

        loginButton = findViewById(R.id.loginButton);
        userEmail = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
    }

    private void initInstance() {
        builder = new AlertDialog.Builder(this);
    }

    private void setListeneres() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:{
                hitLoginApi1();
            }
        }
    }

    private void showAlertDialog(String title, String message){
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void hitLoginApi(){



//           HashMap<String, String> map = new HashMap<>();
//           map.put("grant_type","password");
//           map.put("client_id","2");
//           map.put("client_secret","14IW2kQCb7UkQvZO3qmUaKPAI5T5FN3gGnVbohU5");
//           map.put("username","sheetal.ranium+opo@gmail.com");
//           map.put("password","12345678");

            AuthRquest authRquest = new AuthRquest("password","2","14IW2kQCb7UkQvZO3qmUaKPAI5T5FN3gGnVbohU5",
                    userEmail.getText().toString(),password.getText().toString());

            Call<AuthResponse> authResponseCall = new RetrofitClient().provideRetrofit().create(ApiInterface.class).getLoginApiResponse(authRquest);
            authResponseCall.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {


               if (response.isSuccessful()){

                   System.out.println(">>>>>> response successfull " + response.body().getAccessToken());
                   accessToken = response.body().getAccessToken();
                   SessionManager.getInstance(LoginActivity.this).createLoginSession(accessToken);

                   openMainActivity();


               }else {
                   Gson gson = new Gson();
                   System.out.println(">>>>>> response error before " + response.errorBody().charStream());
                   AuthResponseError error = gson.fromJson(response.errorBody().charStream(), AuthResponseError.class);
                   System.out.println(">>>>>> response error " + error.message);

               }

                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    System.out.println(">>>>>> onFailure " + t.toString());
                }
            });

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private void openMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void hitLoginApi1(){

        HashMap<String, String> map = new HashMap<>();
           map.put("grant_type","password");
           map.put("client_id","2");
           map.put("client_secret","14IW2kQCb7UkQvZO3qmUaKPAI5T5FN3gGnVbohU5");
          // map.put("username","sheetal.ranium+opo@gmail.com");
           map.put("username",userEmail.getText().toString());
          // map.put("password","12345678");
           map.put("password",password.getText().toString());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.CONTENT_TYPE,Constants.CONTENT_TYPE_VALUE);

        Call<AuthResponse> authResponseCall = new RetrofitClient().getClient(hashMap).create(ApiInterface.class).getAuthenticateUser(map);
        authResponseCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {


                if (response.isSuccessful()){

                    System.out.println(">>>>>> response successfull " + response.body().getAccessToken());
                    accessToken = response.body().getAccessToken();
                    SessionManager.getInstance(LoginActivity.this).createLoginSession(accessToken);

                    openMainActivity();


                }else {
                    Gson gson = new Gson();
                    System.out.println(">>>>>> response error before " + response.errorBody().charStream());
                    AuthResponseError error = gson.fromJson(response.errorBody().charStream(), AuthResponseError.class);
                    System.out.println(">>>>>> response error " + error.message);

                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                System.out.println(">>>>>> onFailure " + t.toString());
            }
        });

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

}