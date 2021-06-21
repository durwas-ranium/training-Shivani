package com.testing.mvvm_java;

import com.testing.mvvm_java.pojo.AuthResponse;
import com.testing.mvvm_java.pojo.AuthRquest;
import com.testing.mvvm_java.pojo.companyPojo.AllCompanyResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

//    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMGVlNGJmNDE4MTFmOWY4ZjQ5MzQ2YTU4NGNkYzczNmZmNjI1MTcyNjI0ZmVlYzE1YjNhYzE1OTg2NzQxNDZjZDkwYTkxZTc0Y2Q5MGI3MGEiLCJpYXQiOjE2MjM5MTM0MDUsIm5iZiI6MTYyMzkxMzQwNSwiZXhwIjoxNjU1NDQ5NDA1LCJzdWIiOiIyOCIsInNjb3BlcyI6W119.f0C_sgPHhHl69jzjSRMAVVsfmBly3rk_YsZcw0FG0iqOWNaDPor5sx2QvtFHCtkF8sn1hCT8pSHshQb1ThE5ppfmDOoBkxVzpfqQAXrLYC-zd21HFocLu9ujDwbRmvHbtvMevaX10Opec5V8u16zn_QsZ3GJePTBjAbLZhsmjD-bY3q5Bj-FtaBZcmtmkZIl6GodV9IvwiXG6aqDja0aJITpoxlBs1ug88whH_1Eeq1TA8CYHO-7VJwTZyMM6JmTzff92OiTgZ15vTTUyipIYtnRYG2fpZNUdV6GaT5N1yG5i1dcLy-380kdMBjtzIqzyPoh1Gx7btITx1PaTz40_VEyBQTZE4GAGlHxObhXQgOH2vpwoadLtujv-i7fACdzFsmSHp2N0zbSza0qG6lEBSJvMVC1jELQP6FfJrw1xsECoolIREAjhUg19PUgbhwfu1SmJ0eO4SFURM0B6H91AEnNiv2sYM2K8zNpNJnmU8hGeUaSR1aO2x8-p3IO80jNx5N0whhmk6Sq21WDUcXmmSlRVHTArKUjXRsFOx9DuQwqDmudeB2rcWsc3ACvIJfwZsm4mQAaEDEKOlKt6g8i6A3FfKXjtBJmrBntn34qmBs_00VOnAsOeFxZn849DWENLbzZ5g_n7Sjgb92Jgxr2YNEL8tfL-WWP6rcd4rt-3rE";

@Headers({"Content-Type: application/json"})
@POST("oauth/token")
Call<AuthResponse> getLoginApiResponse(@Body AuthRquest body);

//@Headers({"Content-Type: application/json","Authorization :" + "Bearer "+ token})
@GET("api/company")
Call<AllCompanyResponse> getAllCompanyResponse();

@FormUrlEncoded
@POST("oauth/token")
Call<AuthResponse> getAuthenticateUser(@FieldMap Map<String, String> options);

}
