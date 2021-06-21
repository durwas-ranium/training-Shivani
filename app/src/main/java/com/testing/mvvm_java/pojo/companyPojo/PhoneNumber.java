
package com.testing.mvvm_java.pojo.companyPojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PhoneNumber {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_code")
    @Expose
    private Integer countryCode;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("extension")
    @Expose
    private Object extension;
    @SerializedName("phone_number_type_id")
    @Expose
    private Integer phoneNumberTypeId;
    @SerializedName("verified_at")
    @Expose
    private Object verifiedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public Integer getPhoneNumberTypeId() {
        return phoneNumberTypeId;
    }

    public void setPhoneNumberTypeId(Integer phoneNumberTypeId) {
        this.phoneNumberTypeId = phoneNumberTypeId;
    }

    public Object getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Object verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}
