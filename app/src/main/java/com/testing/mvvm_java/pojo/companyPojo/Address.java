
package com.testing.mvvm_java.pojo.companyPojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("google_verified_at")
    @Expose
    private Object googleVerifiedAt;
    @SerializedName("google_url")
    @Expose
    private Object googleUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getGoogleVerifiedAt() {
        return googleVerifiedAt;
    }

    public void setGoogleVerifiedAt(Object googleVerifiedAt) {
        this.googleVerifiedAt = googleVerifiedAt;
    }

    public Object getGoogleUrl() {
        return googleUrl;
    }

    public void setGoogleUrl(Object googleUrl) {
        this.googleUrl = googleUrl;
    }

}
